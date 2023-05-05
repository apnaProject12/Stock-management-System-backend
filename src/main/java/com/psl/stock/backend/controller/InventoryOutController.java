package com.psl.stock.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.psl.stock.backend.entities.InventoryOut;
import com.psl.stock.backend.entities.InventoryOutItem;
import com.psl.stock.backend.entities.Response;
import com.psl.stock.backend.services.InventoryOutService;
import com.psl.stock.backend.services.inventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/StockInInventory")
public class InventoryOutController {
    @Autowired
private InventoryOutService service;

@Autowired
private inventoryService service2;

private Exception exception;

@PostMapping("InventoryOut/addData")
    public ResponseEntity<Response>  adddata(@Valid @RequestBody InventoryOut inventoryOut) throws Exception{
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
    //     iOut.getInventoryOutItem().stream().forEach(e->{
    //     int productvalue=0;
    //     productvalue=e.getTotalQty();
    //    Inventory inventory=  service2.InvertoryByProductName(e.getProductName()).get();
    //    if (inventory.getProductQty()>=productvalue) {
    //     System.out.println("this is working");
    //       inventory.setProductQty(inventory.getProductQty()-productvalue);
    //       this.service2.addInvertory(inventory);
    //   }
    //  else{
    //     System.out.println("this  is not working");
    //  }
    // });

    for (InventoryOutItem  item2: iOut.getInventoryOutItem()) {
    Optional<Inventory> inventory=   this.service2.InvertoryByProductName(item2.getProductName());
    if (inventory.get().getProductQty()>=item2.getTotalQty()) {
        inventory.get().setProductQty(inventory.get().getProductQty()-item2.getTotalQty());
        this.service2.addInvertory(inventory.get());
    } else {
        return new ResponseEntity<Response>(new Response("value is more"),HttpStatus.OK);
    }
    }
    
     

        return new ResponseEntity<Response>(new Response("Inverntory Out Data Inserted Successfullt"),HttpStatus.OK);
    }
     else {
        return new ResponseEntity<Response>(new Response("Inverntory Out Data didn`t Inserted  Successfully"),HttpStatus.OK);
    }
}

    @GetMapping("/invertoryOut/findAll")
    public ResponseEntity<Page<InventoryOut>> findAll(@RequestParam("pageNo") int pageNo,
    @RequestParam("pageSize") int pageSize, @RequestParam("field") String field,
    @RequestParam("sortDir") String sort) {
        return new ResponseEntity<Page<InventoryOut>>(this.service.getOutList(pageNo, pageSize, field, sort),HttpStatus.OK);
    
    }
    @GetMapping("/invertoryOut/findByFrom/{id}")
    public ResponseEntity<List<InventoryOutItem>> findAll(@PathVariable("id")long id) {
        System.out.println(id);
        return new ResponseEntity<List<InventoryOutItem>>(this.service.findOutItemByFrom(id),HttpStatus.OK);
    
    }

    @DeleteMapping("/inventoryOut/deleteById/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable("id") long id) throws Exception {
        this.service.deleteAllOut(id);
    return    new ResponseEntity<Response>(new Response("inventory out data deleted successfully"),HttpStatus.OK);
    }

    @GetMapping("/inventoryOut/findAlldata/{id}")
    public ResponseEntity<InventoryOut> findfromId23(@PathVariable("id")long id)throws Exception {
        return new ResponseEntity<InventoryOut>(this.service.findInventoryOutById(id),HttpStatus.OK);
    }
}
