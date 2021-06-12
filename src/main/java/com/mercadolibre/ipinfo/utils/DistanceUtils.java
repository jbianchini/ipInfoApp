package com.mercadolibre.ipinfo.utils;

/**
 * Utility class that provides different distance operations.
 *
 * @author jbianchini
 *
 */
public class DistanceUtils {

	/**
	 * Third party software. Please read the description:
	 *
	 * This routine calculates the distance between two points (given the
	 * latitude/longitude of those points). It is being used to calculate the
	 * distance between two locations.
	 *
	 * Definitions: Southern latitudes are negative, eastern longitudes are positive
	 *
	 * Function parameters: lat1, lon1 = Latitude and Longitude of point 1 (in
	 * decimal degrees) lat2, lon2 = Latitude and Longitude of point 2 (in decimal
	 * degrees) unit = the unit you desire for results where: 'M' is statute miles
	 * (default) 'K' is kilometers 'N' is nautical miles Worldwide cities and other
	 * features databases with latitude longitude are available at
	 * https://www.geodatasource.com
	 *
	 * For enquiries, please contact sales@geodatasource.com
	 *
	 * Official Web site: https://www.geodatasource.com
	 *
	 * GeoDataSource.com (C) All Rights Reserved 2019
	 *
	 * @param from {@link Double} lat, long.
	 * @param to   {@link Double} lat, long.
	 * @param unit {@link String} unit (KM for kilometers, N for nautical miles,
	 *             else will be miles)
	 * @return {@link Double} distance in the selected unit.
	 */
	public static double distance(final Double[] from, final Double[] to, final String unit) {

		double lat1 = from[0];
		double lon1 = from[1];

		double lat2 = to[0];
		double lon2 = to[1];

		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit.equals("K")) {
				dist = dist * 1.609344;
			} else if (unit.equals("N")) {
				dist = dist * 0.8684;
			}
			return (dist);
		}
	}
}