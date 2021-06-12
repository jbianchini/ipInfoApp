package com.mercadolibre.ipinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.ipinfo.persistence.model.Distance;
import com.mercadolibre.ipinfo.service.DistanceInfoService;

/**
 * This class provides REST end points to do some distance operations.
 *
 * @author jbianchini
 *
 */
@RestController
@RequestMapping("/dist")
public class DistanceController {

	@Autowired
	private DistanceInfoService service;

	/**
	 * Returns the farthest distance from Buenos Aires that has been consulted.
	 *
	 * @return {@link Distance}
	 */
	@RequestMapping("/max")
	public Distance getMaxDistanceFromBsAs() {
		return this.service.getMaxDistanceFromBsAs();
	}

	/**
	 * Returns the nearest distance from Buenos Aires that has been consulted.
	 *
	 * @return {@link Distance}
	 */
	@RequestMapping("/min")
	public Distance getMinDistanceFromBsAs() {
		return this.service.getMinDistanceFromBsAs();
	}

	/**
	 * Returns an average distance of all executions that have been made of the
	 * service. The value represents the sum of all entries distances multiplied by
	 * entry calls divided the total amount of calls.
	 *
	 * @return {@link Double}
	 */
	@RequestMapping("/avg")
	public Double getAverageDistanceFromBsAs() {
		return this.service.getAverageDistanceFromBsAs();
	}

}
