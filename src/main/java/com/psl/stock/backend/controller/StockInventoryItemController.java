package com.psl.stock.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.psl.stock.backend.entities.StockInventoryItem;
import com.psl.stock.backend.services.StockInventoryItemService;

// @CrossOrigin
@RestController
@RequestMapping("/StockInInventory")
public class StockInventoryItemController {

	@Autowired
	private StockInventoryItemService stockInventoryItemService;

	@GetMapping("/inventory/get/item")
	private List<StockInventoryItem> getAll() {
		return stockInventoryItemService.getAll();
	}

	@GetMapping("/getById/{id}")
	private StockInventoryItem getById(@PathVariable Long id) {
		return stockInventoryItemService.getById(id);
	}

	@PostMapping("/add/item")
	private StockInventoryItem addItem(@RequestBody StockInventoryItem stockInventoryItem) {
		return stockInventoryItemService.addOrUpdate(stockInventoryItem);
	}

	@PutMapping("/addById/{id}")
	private StockInventoryItem updateItem(@PathVariable Long id,
			@RequestBody StockInventoryItem stockInventoryItem) {
		stockInventoryItem.setProductId(id);
		return stockInventoryItemService.addOrUpdate(stockInventoryItem);
	}

	@DeleteMapping("/delete/{id}")
	private void delete(@PathVariable Long id) {
		stockInventoryItemService.deleteById(id);
	}
	@GetMapping("/getProductWithtotal")
	// public ResponseEntity<List<Map<String,Number>>> getProductwithtotal(){
	// 	return new ResponseEntity<List<Map<String,Number>>>(this.stockInventoryItemService.getProductwithTotal(),HttpStatus.ACCEPTED);
	// }
	public int totalProduct(@RequestParam("product")String product) {
		System.out.println(product);
		return this.stockInventoryItemService.getTotalProduct(product);
	}

}
