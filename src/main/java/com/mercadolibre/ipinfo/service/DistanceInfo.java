package com.mercadolibre.ipinfo.service;

import com.mercadolibre.ipinfo.persistence.model.Distance;

/**
 * Interface defining Distance operation methods. User of this interface can get
 * result from different distance operations such as min, max and average
 * distances. Also provides some search and persisting methods.
 *
 * @author jbianchini
 *
 */
public interface DistanceInfo {

	/**
	 * Gets the distance between a pair of coordinates (lat, long) and the
	 * coordinates of Buenos Aires, Argentina expressed in kilometers.
	 *
	 * @param from {@link Double} array containing latitude and longitude
	 * @return Double distance in kilometers
	 */
	public Double calculateDistanceToBsAs(final Double[] from);

	/**
	 * Gets the maximum distance value registered.
	 *
	 * @return {@link Distance} representing distance and some extra information.
	 */
	public Distance getMaxDistanceFromBsAs();

	/**
	 * Gets the minimum distance value registered.
	 *
	 * @return {@link Distance} representing distance and some extra information.
	 */
	public Distance getMinDistanceFromBsAs();

	/**
	 * Returns the average distance registered.
	 *
	 * @return {@link Double} containing the value in kilometers.
	 */
	public Double getAverageDistanceFromBsAs();

	/**
	 * Saves a {@link Distance}
	 *
	 * @param distance {@link Distance} to save.
	 */
	public void saveDistance(final Distance distance);

	/**
	 * Finds {@link Distance} by country code.
	 *
	 * @param countryCode {@link String} representing country code.
	 * @return {@link Distance}
	 */
	public Distance findByCountry(final String countryCode);

}
