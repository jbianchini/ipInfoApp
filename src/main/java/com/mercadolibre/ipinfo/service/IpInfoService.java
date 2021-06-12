package com.mercadolibre.ipinfo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.ipinfo.config.SymbolConfig;
import com.mercadolibre.ipinfo.persistence.model.Distance;
import com.mercadolibre.ipinfo.response.CountryInfo;
import com.mercadolibre.ipinfo.response.Currency;
import com.mercadolibre.ipinfo.response.GeoIp;
import com.mercadolibre.ipinfo.service.external.ApiConsultantService;
import com.mercadolibre.ipinfo.utils.DateTimeUtils;

/**
 * Provides IP consulting operations.
 *
 * @author jbianchini
 *
 */
@Service
public class IpInfoService implements IpInfo {

	@Autowired
	private SymbolConfig symbolConfig;

	@Autowired
	private ApiConsultantService apiConsultantService;

	@Autowired
	private DistanceInfoService distanceInfoService;

	@Override
	public CountryInfo getIpInfo(final String ip) {
		GeoIp geoIp = this.getGeoIp(ip);

		// with the country code, call the second api to get the rest of the info
		CountryInfo countryInfo = this.getCountryInfo(geoIp.getCountryCode3());

		// now that we have the country info , let's get the USD values.
		this.getUSDCurrencies(countryInfo);

		// calculate distance between country and Buenos Aires
		countryInfo.setDistanceToBsAs(this.distanceInfoService.calculateDistanceToBsAs(countryInfo.getLatlng()));

		// store the distance info in db
		Distance distance = this.distanceInfoService.findByCountry(countryInfo.getAlpha3Code());
		if (Objects.isNull(distance)) {
			distance = Distance.builder().country(countryInfo.getAlpha3Code()).distance(countryInfo.getDistanceToBsAs())
					.build();
		}
		distance.addCall();
		this.distanceInfoService.saveDistance(distance);

		return countryInfo;
	}

	/**
	 * Consults an external API to get the geo information about an IP.
	 *
	 * @param ip {@link String} IP.
	 * @return {@link GeoIp} containing geo information.
	 */
	private GeoIp getGeoIp(final String ip) {
		return this.apiConsultantService.getGeoIp(ip);
	}

	/**
	 * Consults an external API to get country information using the country code.
	 *
	 * @param countryCode {@link String} containing the country code.
	 * @return {@link CountryInfo} representing the country info.
	 */
	private CountryInfo getCountryInfo(final String countryCode) {

		CountryInfo countryInfo = this.apiConsultantService.getCountryInfo(countryCode);

		// Sets the time
		this.setTime(countryInfo);

		return countryInfo;
	}

	/**
	 * Sets the time for the country using UTC for its time zone. If has more than
	 * one time zone uses the first one.
	 *
	 * @param countryInfo
	 */
	private void setTime(final CountryInfo countryInfo) {
		if (Objects.nonNull(countryInfo.getTimezones()) && countryInfo.getTimezones().length > 0) {
			countryInfo.setDateTime(DateTimeUtils.ofTimeZone(countryInfo.getTimezones()[0]));
		}
	}

	/**
	 * Obtains the currency of a country expressed in USD.
	 *
	 * @param countryInfo {@link CountryInfo} containing the list of currencies for
	 *                    that country.
	 */
	private void getUSDCurrencies(final CountryInfo countryInfo) {
		// Iterate over the country currencies
		for (Currency c : countryInfo.getCurrencies()) {
			// Get USD value for each currency
			c.setUsdCurrency(this.apiConsultantService.getCurrency(c.getCode(), this.symbolConfig.getUsd()));
		}
	}

}
