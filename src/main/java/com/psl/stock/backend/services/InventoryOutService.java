package com.psl.stock.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.InventoryOut;
import com.psl.stock.backend.repositories.InventoryOutRepo;

@Service
public class InventoryOutService {

    @Autowired
    private InventoryOutRepo repo;

    public InventoryOut AddOutInventory(InventoryOut inventoryOut) {
        return this.repo.save(inventoryOut);
    }
    
}
