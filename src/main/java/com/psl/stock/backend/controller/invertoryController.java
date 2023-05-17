package com.psl.stock.backend.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psl.stock.backend.entities.Inventory;
import com.psl.stock.backend.entities.Response;
import com.psl.stock.backend.services.inventoryService;

@RestController
@RequestMapping("/StockInInventory")
public class invertoryController {

    @Autowired
    inventoryService inventoryService;
    @PostMapping("/addInventory")
    public ResponseEntity<Response> addInventory(@RequestBody Inventory inventory) {
        

        
        System.out.println("inventory out : "+inventory.getProductName());
      for (Inventory element : this.inventoryService.getAllInventory()) {
        System.out.println(element.getProductName());
        if (element.getProductName().equalsIgnoreCase(inventory.getProductName())) {
            return new ResponseEntity<Response>(new Response("data alreadty exist"),HttpStatus.OK);
        }
        
      }
      this.inventoryService.addInvertory(inventory);
        return new ResponseEntity<Response>(new Response("Item added successfully"),HttpStatus.OK);
        
    }
    
    @GetMapping("/findAllInventory")
    public ResponseEntity<List<Inventory>> name() {
        return new ResponseEntity<List<Inventory>>(this.inventoryService.getAllInventory(),HttpStatus.OK);
    }
    @GetMapping("/Inventory/findByProductName/{product}")
    public ResponseEntity<Optional<Inventory>> productByName(@PathVariable("product")String product) {
        return new ResponseEntity<Optional<Inventory>>(this.inventoryService.InvertoryByProductName(product),HttpStatus.OK);
    }

    @GetMapping("/Inventory/AdvanceSearch")
    public ResponseEntity<List<Inventory>> InventorySearch(@RequestParam("product") String productName,@RequestParam("productQty") int productQty) {
        System.out.println(productName);
        return new ResponseEntity<List<Inventory>>(this.inventoryService.searchInventory(productName, productQty),HttpStatus.OK);
    }

    @DeleteMapping("/Inventory/daletedata/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable("id")long id) throws Exception {
        this.inventoryService.deleteInventory(id);
        return new ResponseEntity<Response>(new Response("data deleted successfully"),HttpStatus.OK);
    }
    
}
