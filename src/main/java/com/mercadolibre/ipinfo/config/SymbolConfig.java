package com.mercadolibre.ipinfo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration class containing different symbols used in the application.
 *
 * @author jbianchini
 *
 */
@Component
@ConfigurationProperties(prefix = "symbol")
@Getter
@Setter
public class SymbolConfig {

	private String usd;

	private String km;

}
