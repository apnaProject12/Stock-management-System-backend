package com.psl.stock.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.LogisticsEntities;
import com.psl.stock.backend.repositories.LogisticsRepo;

@Service
public class LogisticsService {
	
	@Autowired
	private LogisticsRepo logisticsRepo;
	
	public LogisticsEntities addOrUpdate(LogisticsEntities logisticsEntities) {
		return logisticsRepo.save(logisticsEntities);
	}

	
	public boolean deleteById(Long id) {
		logisticsRepo.deleteById(id);
		return true;
		
	}
	
	public List<LogisticsEntities> getAll(){
		return logisticsRepo.findAll();
	}
	
	public LogisticsEntities getById(Long id) {
		return logisticsRepo.findById(id).get();
	
	}

}
