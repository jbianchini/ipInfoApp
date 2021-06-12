package com.mercadolibre.ipinfo.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * This object represents a Distance containing a country code, a distance in
 * kilometers and an amount of calls.
 *
 * @author jbianchini
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Distance {

	public static final Distance emptyDistance = Distance.builder().country("There's no distance registered yet")
			.distance(0.00).build();

	@Id
	private String id;

	@Indexed(unique = true)
	@NonNull
	private String country;

	@NonNull
	private Double distance;

	@Builder.Default
	private Integer calls = 0;

	/**
	 * Increments in 1 the amount of calls.
	 */
	public void addCall() {
		this.calls++;
	}

}
