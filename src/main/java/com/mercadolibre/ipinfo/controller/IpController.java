package com.mercadolibre.ipinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.ipinfo.response.CountryInfo;
import com.mercadolibre.ipinfo.service.IpInfoService;

/**
 * This class provides REST end points to retrieve IP information.
 *
 * @author jbianchini
 *
 */
@RestController
@RequestMapping("/info")
public class IpController {

	@Autowired
	private IpInfoService service;

	/**
	 * Retrieves information about a the country which the IP is from.
	 *
	 * @param ip {@link String} representing an IP
	 * @return {@link CountryInfo}
	 */
	@RequestMapping("/{ip}")
	public CountryInfo getIpInfo(@PathVariable final String ip) {
		return this.service.getIpInfo(ip);
	}

}
