package com.mercadolibre.ipinfo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an external API currency data.
 *
 * @author jbianchini
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Currency {

	private String name;
	private String code;
	private Double usdCurrency;

}
