package com.mercadolibre.ipinfo.service.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.ipinfo.config.KeyConfig;
import com.mercadolibre.ipinfo.config.UrlConfig;
import com.mercadolibre.ipinfo.response.CountryInfo;
import com.mercadolibre.ipinfo.response.GeoIp;
import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;

/**
 * Provides consults for different external APIs such as Geo information for an
 * IP, country information and currency conversion.
 *
 * @author jbianchini
 *
 */
@Service
public class ApiConsultantService implements ApiConsultant {

	@Autowired
	private KeyConfig keyConfig;

	@Autowired
	private UrlConfig urlConfig;

	@Override
	public GeoIp getGeoIp(final String ip) {

		return new RestTemplate().getForObject(this.urlConfig.getGeoIp() + ip, GeoIp.class);
	}

	@Override
	public CountryInfo getCountryInfo(final String countryCode) {
		return new RestTemplate().getForObject(this.urlConfig.getCountryInfo() + countryCode, CountryInfo.class);
	}

	@Override
	public Double getCurrency(final String base, final String convertTo) {
		CurrencyConverter converter = new CurrencyConverter(
				new ConfigBuilder().currencyConverterApiApiKey(this.keyConfig.getCurrencyConverterKey()).build());

		return converter.rate(base, convertTo);

	}

}
