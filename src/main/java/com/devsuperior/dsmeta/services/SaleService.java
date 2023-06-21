package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.devsuperior.dsmeta.utils.DateUtil.getMaxDate;
import static com.devsuperior.dsmeta.utils.DateUtil.getMinDate;

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

	public Page<SaleSummaryDTO> generateSummary(String minDate, String maxDate, Pageable pageable) {
		LocalDate max = getMaxDate(maxDate);
		LocalDate min = getMinDate(max, minDate);
		return repository.generateSummary(min, max, pageable);
	}
}
