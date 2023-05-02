package com.psl.stock.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.psl.stock.backend.entities.Inventory;
import com.psl.stock.backend.entities.Response;
import com.psl.stock.backend.entities.StockInInventory;
import com.psl.stock.backend.entities.StockInventoryItem;
import com.psl.stock.backend.services.StockInInventoryService;
import com.psl.stock.backend.services.StockInventoryItemService;
import com.psl.stock.backend.services.inventoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

// @CrossOrigin
@RestController

@RequestMapping("/StockInInventory")
public class StockInInventoryController {
    @Autowired
	private  StockInInventoryService stockInInventoryService;

	@Autowired 
	private StockInventoryItemService stockInventoryItemService;

	@Autowired
	private inventoryService inventoryService;

	@GetMapping("/inventory/item")
	public ResponseEntity<Page<StockInInventory>> getAll(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize, @RequestParam("field") String field,
			@RequestParam("sortDir") String sort) {
		return new ResponseEntity<Page<StockInInventory>>(stockInInventoryService.getAll(pageNo, pageSize, field, sort),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private StockInInventory getById(@PathVariable Long id) {
		return stockInInventoryService.getById(id);
	}

	@PostMapping("/add/inventory")
	public ResponseEntity<Response> addItem(@Valid @RequestBody StockInInventory stockInInventory) {
	StockInInventory s=	stockInInventoryService.addOrUpdate(stockInInventory);
		if (s!=null) {
			return new ResponseEntity<Response>(new Response("Stock Inventory add successfully created successfully"), HttpStatus.CREATED);
		}
		else{ 
			return new ResponseEntity<Response>(new Response("Something went wrong"), HttpStatus.CREATED);
			
			
		}
		
	}

	@PutMapping("/{id}")
	private StockInInventory updateItem(@PathVariable Long id,
			@RequestBody StockInInventory stockInInventory) {
		stockInInventory.setId(id);
		return stockInInventoryService.addOrUpdate(stockInInventory);
	}

	@DeleteMapping("/{id}")
	private void delete(@PathVariable Long id) {
		stockInInventoryService.deleteById(id);
	}

	// @GetMapping("{id}/getproduct")
	// public List<Map<String,Object>> getProductByInventoryId(@PathVariable("id")
	// Long id){
	//
	// return stockInInventoryService.getProductByInventoryId(id);
	// }

	@GetMapping("/getproduct/{id}")
	public ResponseEntity<List<StockInInventory>> getProductItemById(@PathVariable("id") Long id) {
		System.out.println(id);
		return new ResponseEntity<List<StockInInventory>>(this.stockInInventoryService.getItemById(id), HttpStatus.OK);
	}

	@GetMapping("/getProduct/approval")
    public ResponseEntity<Response> getwithApproval(@RequestParam("id")Long id,@RequestParam("approval")boolean approval){
         StockInInventory byId = this.stockInInventoryService.getById(id);
         byId.setIsApproved(approval);
	StockInInventory stockInInventory=	this.stockInInventoryService.addOrUpdate(byId);
	 boolean appro= stockInInventory.getIsApproved();
        if (appro) {
			long productValue;
           for (StockInventoryItem item : stockInInventory.getStockInventoryItems()) {
			 productValue=this.stockInventoryItemService.getTotalProduct(item.getProductName());
			Optional<Inventory> inventory= this.inventoryService.InvertoryByProductName(item.getProductName());
			inventory.get().setProductQty(productValue);
			this.inventoryService.updateProductQuantity(inventory);;
			
		   }

            return new ResponseEntity<Response>(new Response("Approved successfully"),HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Response>(new Response("Approval unsuccessful"),HttpStatus.BAD_GATEWAY);
        }
    }
}

