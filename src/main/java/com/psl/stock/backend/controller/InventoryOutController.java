package com.psl.stock.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.stock.backend.entities.InventoryOut;
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
    InventoryOut iOut=this.service.AddOutInventory(inventoryOut);
    if (iOut !=null) {
        return new ResponseEntity<Response>(new Response("Inverntory Out Data Inserted Successfullt"),HttpStatus.OK);
    } else {
        return new ResponseEntity<Response>(new Response("Inverntory Out Data didn`t Inserted  Successfully"),HttpStatus.OK);
    }
    }
}
