package com.mercadolibre.ipinfo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mercadolibre.ipinfo.persistence.IDistanceRepository;
import com.mercadolibre.ipinfo.persistence.model.Distance;

import lombok.extern.java.Log;

@Log
@SpringBootTest
class DistanceInfoServiceTest {

	private static double distanceToBsAs;
	private static Double[] someCoord;

	private static Distance maxDistance;
	private static Distance minDistance;
	private static Distance anotherDistance;
	private static List<Distance> distances;

	private static Double average;

	@Autowired
	private DistanceInfo distanceInfo;

	@MockBean
	private IDistanceRepository distanceRepository;

	@BeforeAll
	public static void setup() {
		someCoord = new Double[] { -50.0, 34.0 };
		distanceToBsAs = 4784.85959205022;

		maxDistance = Distance.builder().country("USA").distance(7654.23).calls(3).build();
		minDistance = Distance.builder().country("BRA").distance(2345.45).calls(1).build();
		anotherDistance = Distance.builder().country("MEX").distance(4000.00).calls(2).build();

		distances = Arrays.asList(maxDistance, anotherDistance, minDistance);

		average = ((minDistance.getDistance() * minDistance.getCalls())
				+ (anotherDistance.getDistance() * anotherDistance.getCalls())
				+ (maxDistance.getDistance() * maxDistance.getCalls()))
				/ (minDistance.getCalls() + anotherDistance.getCalls() + maxDistance.getCalls());
	}

	@BeforeEach
	public void init() {

		Mockito.when(this.distanceRepository.findFirstByOrderByDistanceDesc()).thenReturn(maxDistance);
		Mockito.when(this.distanceRepository.findFirstByOrderByDistanceAsc()).thenReturn(minDistance);
		Mockito.when(this.distanceRepository.findAll()).thenReturn(distances);
	}

	@Test
	void calculateDistance_resultIsNotNull() {
		log.info("Running test whenCoordinatesNotNull_resultIsNotNull");
		assertNotEquals(this.distanceInfo.calculateDistanceToBsAs(someCoord), null);
	}

	@Test
	void whenCoordinatesAreNull_resultIsZero() {
		log.info("Running test whenCoordinatesAreNull_resultIsZero");
		assertEquals(this.distanceInfo.calculateDistanceToBsAs(null), 0.00);
	}

	@Test
	void whenCoordinatesAreEmpty_resultIsZero() {
		log.info("Running test whenCoordinatesAreEmpty_resultIsZero");
		assertEquals(this.distanceInfo.calculateDistanceToBsAs(new Double[] {}), 0.00);
	}

	@Test
	void calculateDistanceToBsAs() {
		log.info("Running test calculateDistanceToBsAs");
		assertEquals(this.distanceInfo.calculateDistanceToBsAs(someCoord), distanceToBsAs);
	}

	@Test
	void testGetMaxDistanceFromBsAs() {
		log.info("Running test testGetMaxDistanceFromBsAs");
		assertEquals(this.distanceInfo.getMaxDistanceFromBsAs(), maxDistance);
	}

	@Test
	void testGetMinDistanceFromBsAs() {
		log.info("Running test testGetMinDistanceFromBsAs");
		assertEquals(this.distanceInfo.getMinDistanceFromBsAs(), minDistance);
	}

	@Test
	void testGetAverageDistanceFromBsAs() {
		log.info("Running test testGetAverageDistanceFromBsAs");
		assertEquals(this.distanceInfo.getAverageDistanceFromBsAs(), average);
	}

}
