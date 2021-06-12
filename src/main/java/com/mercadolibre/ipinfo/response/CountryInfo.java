package com.mercadolibre.ipinfo.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an external API response with country data.
 *
 * @author jbianchini
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CountryInfo {

	private String name;
	private String alpha3Code;
	private Language[] languages;
	private Double[] latlng;
	private LocalDateTime dateTime;
	private String[] timezones;
	private Currency[] currencies;
	private Double distanceToBsAs;

}
