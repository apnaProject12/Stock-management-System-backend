package com.psl.stock.backend.controller;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.stock.backend.entities.Inventory;
import com.psl.stock.backend.services.inventoryService;

@RestController
@RequestMapping("/StockInInventory")
public class invertoryController {

    @Autowired
    inventoryService inventoryService;
    @PostMapping("/addInventory")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        return new ResponseEntity<Inventory>(this.inventoryService.addInvertory(inventory),HttpStatus.OK);
    }
    
    @GetMapping("/findAllInventory")
    public ResponseEntity<List<Inventory>> name() {
        return new ResponseEntity<List<Inventory>>(this.inventoryService.getAllInventory(),HttpStatus.OK);
    }
    @GetMapping("/Inventory/findByProductName/{product}")
    public ResponseEntity<Optional<Inventory>> productByName(@PathVariable("product")String product) {
        return new ResponseEntity<Optional<Inventory>>(this.inventoryService.InvertoryByProductName(product),HttpStatus.OK);
    }
    
}
