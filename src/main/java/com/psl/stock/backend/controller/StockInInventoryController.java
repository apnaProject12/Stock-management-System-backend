package com.psl.stock.backend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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

import com.psl.stock.backend.entities.StockInInventory;
import com.psl.stock.backend.services.StockInInventoryService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/StockInInventory")
public class StockInInventoryController {
	
	
	private final StockInInventoryService stockInInventoryService;
	
	@GetMapping("/inventory/item")
	public  ResponseEntity<Page<StockInInventory>> getAll(@RequestParam("pageNo")int pageNo,@RequestParam("pageSize")int pageSize,@RequestParam("field")String field,@RequestParam("sortDir")String sort ){
		return new ResponseEntity<Page<StockInInventory>>(stockInInventoryService.getAll(pageNo, pageSize, field, sort),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	private StockInInventory getById(@PathVariable Long id) {
		return stockInInventoryService.getById(id);
	}
	
	@PostMapping("/add/inventory")
	private StockInInventory addItem(@RequestBody StockInInventory stockInInventory) {
		return stockInInventoryService.addOrUpdate(stockInInventory);
	}
	
	@PutMapping("/{id}")
	private StockInInventory updateItem(@PathVariable Long id,
			@RequestBody StockInInventory stockInInventory) {
		stockInInventory.setId(id);
		return stockInInventoryService.addOrUpdate(stockInInventory);
	}
	
	
	@DeleteMapping("/{id}")
	private void delete(@PathVariable Long id ) {
		stockInInventoryService.deleteById(id);
	}
	
//	@GetMapping("{id}/getproduct")
//	public List<Map<String,Object>> getProductByInventoryId(@PathVariable("id") Long id){
//		
//		return stockInInventoryService.getProductByInventoryId(id);
//	}
	
	@GetMapping("/getproduct/{id}")
	public ResponseEntity<List<StockInInventory>> getProductItemById(@PathVariable("id") Long id){
		System.out.println(id);
		return new ResponseEntity<List<StockInInventory>>(this.stockInInventoryService.getItemById(id),HttpStatus.OK);
	}
	

}
