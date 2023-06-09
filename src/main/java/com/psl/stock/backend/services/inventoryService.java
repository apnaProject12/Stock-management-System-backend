package com.psl.stock.backend.services;

import java.util.List;
import java.util.Optional;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.Inventory;
import com.psl.stock.backend.repositories.InventoryRepo;
@Service
public class inventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    public Inventory addInvertory(Inventory inventory) {
       return this.inventoryRepo.save(inventory);
    }
    public List<Inventory> getAllInventory() {
      return  this.inventoryRepo.findAll();
    }
    public Optional<Inventory> InvertoryByProductName(String productName) {
        return this.inventoryRepo.findByProductName(productName);
    }
    public void updateProductQuantity(Optional<Inventory> inventory) {
        this.inventoryRepo.save(inventory.get());
    }

    public List<Inventory> searchInventory(String productName,int productQty) {
        System.out.println("bdjcdc");
        return this.inventoryRepo.searchInverntory(productName, productQty);
    }
    public void deleteInventory(long id) throws Exception {
        this.inventoryRepo.findById(id).orElseThrow(()->new Exception("id not present"));
        this.inventoryRepo.deleteById(id);
    }

    
}
