package com.psl.stock.backend.services;

import java.util.List;
import java.util.stream.Collectors;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.InventoryOut;
import com.psl.stock.backend.entities.InventoryOutItem;
import com.psl.stock.backend.repositories.InventoryOutRepo;

@Service
public class InventoryOutService {

    @Autowired
    private InventoryOutRepo repo;

    public InventoryOut AddOutInventory(InventoryOut inventoryOut) {
        return this.repo.save(inventoryOut);
    }
    public List<List<InventoryOutItem>> getOutInventoryList() {
  return  this.repo.findAll().stream().map(e->e.getInventoryOutItem()).collect(Collectors.toList());
        
    }
    public List<InventoryOut> getOutList() {
      return  this.repo.findAll();
    }

    public   List<InventoryOutItem> findOutItemByFrom(Long id) {
      return this.repo.findById(id).get().getInventoryOutItem();
    }

    
}
