package com.psl.stock.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psl.stock.backend.entities.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory,Long>{
    public Optional<Inventory> findByProductName(String productname);
    
}
