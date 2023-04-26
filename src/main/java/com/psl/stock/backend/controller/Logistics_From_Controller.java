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

import com.psl.stock.backend.entities.AddFromEntity;
import com.psl.stock.backend.services.LogisticsService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/StockInInventory")
public class Logistics_From_Controller {
	
	private final LogisticsService logisticsService;
	
	@GetMapping("/logistics/gell")
	private List<AddFromEntity> getAll(){
		return logisticsService.getAll();
	}
	
	@GetMapping("/logistics/from/{id}")
	private AddFromEntity getById(@PathVariable Long id) {
		return logisticsService.getById(id);
	}
	
	@PostMapping("/logistics/from/add")
	private AddFromEntity addItem(@RequestBody AddFromEntity logisticsEntities) {
		return logisticsService.addOrUpdate(logisticsEntities);
	}
	
	@PutMapping("/logistics/from/{id}")
	private AddFromEntity updateItem(@PathVariable Long id,
			@RequestBody AddFromEntity logisticsEntities) {
		logisticsEntities.setId(id);
		return logisticsService.addOrUpdate(logisticsEntities);
	}
	
	
	@DeleteMapping("/logistics/from/{id}")
	private void delete(@PathVariable Long id) {
		logisticsService.deleteById(id);
	}

}
