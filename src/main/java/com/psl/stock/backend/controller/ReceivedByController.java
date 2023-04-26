package com.psl.stock.backend.controller;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.stock.backend.entities.Logistic_ReceivedBy;
import com.psl.stock.backend.services.ReceivedByService;

@RestController
@RequestMapping("/StockInInventory")
public class ReceivedByController {
    @Autowired
    // LogisticsRepo logisticsRepo;
    // private LogisticsService logisticsService;
    private ReceivedByService receivedByService;

    @PostMapping("/receiver/add")
    public ResponseEntity<?> insertFromData(@RequestBody Logistic_ReceivedBy logistic_ReceivedBy) {
        return new ResponseEntity<Logistic_ReceivedBy>(this.receivedByService.insertReceivedBy(logistic_ReceivedBy),
                HttpStatus.OK);

    }

    @DeleteMapping("/receiver/delete/{id}")
    public ResponseEntity<List<Logistic_ReceivedBy>> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<List<Logistic_ReceivedBy>>(this.receivedByService.deleteData(id), HttpStatus.OK);
    }

    @GetMapping("/receiver/findAll")
    public ResponseEntity<List<Logistic_ReceivedBy>> deleteById() {
        return new ResponseEntity<List<Logistic_ReceivedBy>>(this.receivedByService.findAll(), HttpStatus.OK);
    }

}
