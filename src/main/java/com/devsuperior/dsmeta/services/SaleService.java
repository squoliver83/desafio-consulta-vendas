package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> generateReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate max = getMaxDate(maxDate);
		LocalDate min = getMinDate(max, minDate);
		return repository.generateReport(min, max, name, pageable);
	}

	private LocalDate getMaxDate(String maxDate) {
		if(!maxDate.equals("")) {
			return LocalDate.parse(maxDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	}

	private LocalDate getMinDate(LocalDate max, String minDate) {
		if(!minDate.equals("")) {
			return LocalDate.parse(minDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		return max.minusYears(1L);
	}
}
