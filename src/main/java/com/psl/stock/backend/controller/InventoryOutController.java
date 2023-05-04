package com.psl.stock.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.stock.backend.entities.InventoryOut;
import com.psl.stock.backend.entities.InventoryOutItem;
import com.psl.stock.backend.entities.Response;
import com.psl.stock.backend.services.InventoryOutService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/StockInInventory")
public class InventoryOutController {
    @Autowired
private InventoryOutService service;

@PostMapping("InventoryOut/addData")
    public ResponseEntity<Response>  adddata(@Valid @RequestBody InventoryOut inventoryOut) {
    List<InventoryOutItem> item= inventoryOut.getInventoryOutItem();
    for (InventoryOutItem inventoryOutItem : item) {
        if (inventoryOutItem.getProductName()=="" || inventoryOutItem.getProductName()==null) {
            return new ResponseEntity<Response>(new Response("product Name should not be null"),HttpStatus.BAD_REQUEST);
        }
        if (inventoryOutItem.getTotalQty()<=0 ) {
            return  new ResponseEntity<Response>(new Response("product qty should be greater than 0"),HttpStatus.BAD_REQUEST);
        }
    }
    InventoryOut iOut=this.service.AddOutInventory(inventoryOut);
    if (iOut !=null) {
        return new ResponseEntity<Response>(new Response("Inverntory Out Data Inserted Successfullt"),HttpStatus.OK);
    } else {
        return new ResponseEntity<Response>(new Response("Inverntory Out Data didn`t Inserted  Successfully"),HttpStatus.OK);
    }
}

    @GetMapping("/invertoryOut/findAll")
    public ResponseEntity<List<InventoryOut>> findAll() {
        return new ResponseEntity<List<InventoryOut>>(this.service.getOutList(),HttpStatus.OK);
    
    }
    @GetMapping("/invertoryOut/findByFrom/{id}")
    public ResponseEntity<List<InventoryOutItem>> findAll(@PathVariable("id")long id) {
        System.out.println(id);
        return new ResponseEntity<List<InventoryOutItem>>(this.service.findOutItemByFrom(id),HttpStatus.OK);
    
    }
}
