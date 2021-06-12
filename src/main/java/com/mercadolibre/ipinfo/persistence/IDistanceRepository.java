package com.mercadolibre.ipinfo.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.ipinfo.persistence.model.Distance;

/**
 * Interface extending the Mongo DB Repository and declaring public custom
 * persistence operations.
 *
 * @author jbianchini
 *
 */
@Repository
public interface IDistanceRepository extends MongoRepository<Distance, String> {

	/**
	 * Finds a {@link Distance} entry searching by country code.
	 *
	 * @param country {@link String} with country code.
	 * @return {@link Distance}
	 */
	public Distance findByCountry(String country);

	/**
	 * Finds the highest distance registered.
	 *
	 * @return {@link Distance}
	 */
	public Distance findFirstByOrderByDistanceDesc();

	/**
	 * Finds the lowest distance registered.
	 *
	 * @return
	 */
	public Distance findFirstByOrderByDistanceAsc();

}
