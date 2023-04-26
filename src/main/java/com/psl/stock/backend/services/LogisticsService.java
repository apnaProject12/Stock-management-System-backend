package com.psl.stock.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.AddFromEntity;
import com.psl.stock.backend.repositories.LogisticsRepo;

@Service
public class LogisticsService {

	@Autowired
	private LogisticsRepo logisticsRepo;

	public AddFromEntity addOrUpdate(AddFromEntity logisticsEntities) {
		return logisticsRepo.save(logisticsEntities);
	}

	public boolean deleteById(Long id) {
		logisticsRepo.deleteById(id);
		return true;

	}

	public List<AddFromEntity> getAll() {
		return logisticsRepo.findAll();
	}

	public AddFromEntity getById(Long id) {
		return logisticsRepo.findById(id).get();

	}

}
