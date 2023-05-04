package com.psl.stock.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.stock.backend.entities.InventoryOut;
// import com.psl.stock.backend.entities.InventoryOutItem;
@Repository
public interface InventoryOutRepo extends JpaRepository<InventoryOut,Long>{

    public List<InventoryOut> findByFrom(String fromBy); 
}
