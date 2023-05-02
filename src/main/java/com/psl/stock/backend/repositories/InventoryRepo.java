package com.psl.stock.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.psl.stock.backend.entities.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory,Long>{
    public Optional<Inventory> findByProductName(String productname);
    

@Query(value="SELECT * FROM dummy_stock_db.inventory where product_name   LIKE (%:productName%) or product_qty LIKE (%:productQty%) ",nativeQuery = true)

public List<Inventory> searchInverntory(String productName,int productQty);
}
