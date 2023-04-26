package com.psl.stock.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.psl.stock.backend.entities.StockInventoryItem;
import com.psl.stock.backend.services.StockInventoryItemService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class StockInventoryItemController {

	@Autowired
	private StockInventoryItemService stockInventoryItemService;

	@GetMapping("/inventory/item")
	private List<StockInventoryItem> getAll() {
		return stockInventoryItemService.getAll();
	}

	@GetMapping("/{id}")
	private StockInventoryItem getById(@PathVariable Long id) {
		return stockInventoryItemService.getById(id);
	}

	@PostMapping("/add/item")
	private StockInventoryItem addItem(@RequestBody StockInventoryItem stockInventoryItem) {
		return stockInventoryItemService.addOrUpdate(stockInventoryItem);
	}

	@PutMapping("/{id}")
	private StockInventoryItem updateItem(@PathVariable Long id,
			@RequestBody StockInventoryItem stockInventoryItem) {
		stockInventoryItem.setProductId(id);
		return stockInventoryItemService.addOrUpdate(stockInventoryItem);
	}

	@DeleteMapping("/{id}")
	private void delete(@PathVariable Long id) {
		stockInventoryItemService.deleteById(id);
	}

}
