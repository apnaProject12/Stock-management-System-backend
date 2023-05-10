package com.psl.stock.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.stock.backend.entities.AddProductEntities;
import com.psl.stock.backend.services.AddProductService;

import lombok.AllArgsConstructor;

// @CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/StockInInventory")
public class AddProductController {

	private final AddProductService addProductService;

	@GetMapping("/product-list/gell")
	private List<AddProductEntities> getAll() {
		return addProductService.getAll();
	}

	@GetMapping("/product-list/{id}")
	private ResponseEntity<AddProductEntities> getById(@PathVariable Long id) {
		// return addProductService.getById(id);
		return new ResponseEntity<AddProductEntities>(this.addProductService.getById(id), HttpStatus.OK);
	}

	@PostMapping("/product-list/product")
	private AddProductEntities addItem(@RequestBody AddProductEntities addProductEntities) {
		return addProductService.addOrUpdate(addProductEntities);
	}

	@PutMapping("/product-list/{id}")
	private AddProductEntities updateItem(@PathVariable Long id,
			@RequestBody AddProductEntities addProductEntities) {
		addProductEntities.setId(id);
		return addProductService.addOrUpdate(addProductEntities);
	}

	@DeleteMapping("/product-list/{id}")
	private void delete(@PathVariable Long id) {
		addProductService.deleteById(id);
	}

}
