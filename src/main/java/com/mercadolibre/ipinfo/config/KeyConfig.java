package com.mercadolibre.ipinfo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Key configuration class containing mapped api keys.
 *
 * @author jbianchini
 *
 */
@Component
@ConfigurationProperties(prefix = "key")
@Getter
@Setter
public class KeyConfig {

	private String currencyConverterKey;

}
