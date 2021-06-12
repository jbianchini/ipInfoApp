package com.mercadolibre.ipinfo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.ipinfo.config.DistanceConfig;
import com.mercadolibre.ipinfo.config.SymbolConfig;
import com.mercadolibre.ipinfo.persistence.IDistanceRepository;
import com.mercadolibre.ipinfo.persistence.model.Distance;
import com.mercadolibre.ipinfo.utils.DistanceUtils;

/**
 * Provides {@link Distance} operations like max, min and average and some
 * persistence methods.
 *
 * @author jbianchini
 *
 */
@Service
public class DistanceInfoService implements DistanceInfo {

	@Autowired
	private DistanceConfig distanceConfig;

	@Autowired
	private SymbolConfig symbolConfig;

	@Autowired
	private IDistanceRepository distanceRepository;

	@Override
	public Double calculateDistanceToBsAs(final Double[] from) {
		if (Objects.isNull(from) || from.length == 0) {
			return 0.00;
		}
		Double[] bsAsCoord = new Double[] { this.distanceConfig.getBsasLat(), this.distanceConfig.getBsasLong() };
		return DistanceUtils.distance(from, bsAsCoord, this.symbolConfig.getKm());
	}

	@Override
	public Distance getMaxDistanceFromBsAs() {
		return this.checkNullDistance(this.distanceRepository.findFirstByOrderByDistanceDesc());
	}

	@Override
	public Distance getMinDistanceFromBsAs() {
		return this.checkNullDistance(this.distanceRepository.findFirstByOrderByDistanceAsc());
	}

	@Override
	public Double getAverageDistanceFromBsAs() {
		List<Distance> allDistances = this.distanceRepository.findAll();
		if (allDistances.isEmpty()) {
			return (double) 0;
		}
		Integer totalCalls = allDistances.stream().map(d -> d.getCalls()).reduce(0, Integer::sum);

		Double totalDistancePerCalls = allDistances.stream().map(d -> d.getCalls() * d.getDistance()).reduce(0.00,
				Double::sum);

		return totalDistancePerCalls / totalCalls;
	}

	@Override
	public void saveDistance(final Distance distance) {
		this.distanceRepository.save(distance);
	}

	@Override
	public Distance findByCountry(final String countryCode) {
		return this.distanceRepository.findByCountry(countryCode);
	}

	private Distance checkNullDistance(final Distance result) {
		return Objects.nonNull(result) ? result : Distance.emptyDistance;
	}

}
