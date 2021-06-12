package com.mercadolibre.ipinfo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration class containing different distance values.
 *
 * @author jbianchini
 *
 */
@Component
@ConfigurationProperties(prefix = "distance")
@Getter
@Setter
public class DistanceConfig {

	private Double bsasLat;

	private Double bsasLong;
}
