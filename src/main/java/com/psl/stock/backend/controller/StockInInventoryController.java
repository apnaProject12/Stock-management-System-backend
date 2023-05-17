package com.psl.stock.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@GetMapping("/getInventoryItemById/{id}")
	public ResponseEntity<StockInInventory> findInventoryItemById(@PathVariable("id") long id) throws Exception {
		return new ResponseEntity<StockInInventory>(this.stockInInventoryService.findDataById(id),HttpStatus.OK);
	}

	@PostMapping("/add/inventory")
	public ResponseEntity<Response> addItem(@Valid @RequestBody StockInInventory stockInInventory) {

		for (StockInventoryItem inItem : stockInInventory.getStockInventoryItems()) {
		  if (inItem.getProductName()==null || inItem.getProductName()=="" ) {
			return new ResponseEntity<Response>(new Response("product name must be required"),HttpStatus.BAD_REQUEST);
		  }
		  if ( inItem.getProductQty() <=0 ) {
			return new ResponseEntity<Response>(new Response("product quentity must be greater than 0"),HttpStatus.BAD_REQUEST);
		  }
		  if ( inItem.getPrice() <=0 ) {
			return new ResponseEntity<Response>(new Response("product prices must be greater than 0"),HttpStatus.BAD_REQUEST);
		  }
		}

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

	@DeleteMapping("/StockIn/deleteData/{id}")
	private ResponseEntity<Response> delete(@PathVariable Long id) throws Exception {
		this.stockInInventoryService.deleteById(id);
		return new ResponseEntity<Response>(new Response("data deleted successfully"),HttpStatus.OK);
	}

	// @GetMapping("{id}/getproduct")
	// public List<Map<String,Object>> getProductByInventoryId(@PathVariable("id")
	// Long id){
	//
	// return stockInInventoryService.getProductByInventoryId(id);
	// }

	
	@GetMapping("/getProduct/approval")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Response> getwithApproval(@RequestParam("id")Long id,@RequestParam("approval")boolean approval) throws Exception{
         StockInInventory byId = this.stockInInventoryService.findDataById(id); 
         byId.setIsApproved(approval);
	StockInInventory stockInInventory=	this.stockInInventoryService.addOrUpdate(byId);
	 boolean appro= stockInInventory.getIsApproved();
        if (appro) {
			long productValue;
           for (StockInventoryItem item : stockInInventory.getStockInventoryItems()) {
			 productValue=this.stockInventoryItemService.getTotalProduct(item.getProductName());
			Optional<Inventory> inventory= this.inventoryService.InvertoryByProductName(item.getProductName());
			inventory.get().setProductQty(productValue);
			this.inventoryService.updateProductQuantity(inventory);
			
		   }

            return new ResponseEntity<Response>(new Response("Approved successfully"),HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Response>(new Response("Approval unsuccessful"),HttpStatus.BAD_GATEWAY);
        }
    }
}

