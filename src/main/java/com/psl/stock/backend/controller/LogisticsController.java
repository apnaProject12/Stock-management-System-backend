package com.psl.stock.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.stock.backend.entities.LogisticsEntities;
import com.psl.stock.backend.services.LogisticsService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/logistics")
public class LogisticsController {
	
	private final LogisticsService logisticsService;
	
	@GetMapping("/gell")
	private List<LogisticsEntities> getAll(){
		return logisticsService.getAll();
	}
	
	@GetMapping("/{id}")
	private LogisticsEntities getById(@PathVariable Long id) {
		return logisticsService.getById(id);
	}
	
	@PostMapping("/add")
	private LogisticsEntities addItem(@RequestBody LogisticsEntities logisticsEntities) {
		return logisticsService.addOrUpdate(logisticsEntities);
	}
	
	@PutMapping("/{id}")
	private LogisticsEntities updateItem(@PathVariable Long id,
			@RequestBody LogisticsEntities logisticsEntities) {
		logisticsEntities.setId(id);
		return logisticsService.addOrUpdate(logisticsEntities);
	}
	
	
	@DeleteMapping("/{id}")
	private void delete(@PathVariable Long id) {
		logisticsService.deleteById(id);
	}

}
