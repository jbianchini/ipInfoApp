package com.mercadolibre.ipinfo.service;

import com.mercadolibre.ipinfo.response.CountryInfo;

/**
 * Provides IP operations like consulting information for a specific IP.
 *
 * @author jbianchini
 *
 */
public interface IpInfo {

	/**
	 * Returns information about the passed IP.
	 *
	 * @param ip {@link String} ip.
	 * @return {@link CountryInfo} representing IP information.
	 */
	public CountryInfo getIpInfo(String ip);

}
