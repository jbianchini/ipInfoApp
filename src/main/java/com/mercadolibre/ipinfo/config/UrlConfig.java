package com.mercadolibre.ipinfo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration class containing the urls used in the application.
 *
 * @author jbianchini
 *
 */
@Component
@ConfigurationProperties(prefix = "url")
@Getter
@Setter
public class UrlConfig {

	private String geoIp;

	private String countryInfo;

}
