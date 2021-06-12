package com.mercadolibre.ipinfo.service.external;

import com.mercadolibre.ipinfo.response.CountryInfo;
import com.mercadolibre.ipinfo.response.GeoIp;

/**
 * Provides the methods the application needs to get the IP information.
 *
 * @author jbianchini
 *
 */
public interface ApiConsultant {

	/**
	 * Gets the Geo data for an IP.
	 *
	 * @param ip {@link String} IP
	 * @return {@link GeoIp} info.
	 */
	public GeoIp getGeoIp(String ip);

	/**
	 * Gets country information using country code.
	 *
	 * @param code {@link String} country code.
	 * @return {@link CountryInfo} for that country code.
	 */
	public CountryInfo getCountryInfo(String code);

	/**
	 * Converts currencies expressed on the currency symbol. E.g.: USD to ARS
	 *
	 * @param base      {@link String} base currency.
	 * @param convertTo {@link String} convertTo currency.
	 * @return
	 */
	public Double getCurrency(String base, String convertTo);

}
