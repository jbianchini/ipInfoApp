package com.mercadolibre.ipinfo.utils;

import java.time.LocalDateTime;

/**
 * Provides util methods for date and time.
 *
 * @author jbianchini
 *
 */
public class DateTimeUtils {

	/**
	 * Gets the current time for the passed UTC time zone.
	 *
	 * @param timeZone {@link String} time zone.
	 * @return {@link LocalDateTime} representing the current time in the passed
	 *         time zone.
	 */
	public static LocalDateTime ofTimeZone(final String timeZone) {
		// TODO: Calculate the time of the passed timeZone
		LocalDateTime time = LocalDateTime.now();
		return time;

	}

}
