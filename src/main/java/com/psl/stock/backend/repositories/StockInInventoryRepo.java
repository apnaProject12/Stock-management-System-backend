package com.psl.stock.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.psl.stock.backend.entities.StockInInventory;

@Repository
public interface StockInInventoryRepo extends JpaRepository<StockInInventory, Long> {

	// @Query(value = "SELECT * FROM inventory_item p JOIN `inventory_in` i ON p.`stock_id`=i.`stock_id` WHERE i.stock_id=:id", nativeQuery = true)
	// public List<StockInInventory> stockInventoryItemById(Long id);
// 	@Query(value = "SELECT * FROM inventory_item p JOIN `inventory_in` i ON p.`stock_id`=i.`stock_id` WHERE i.stock_id=:id", nativeQuery = true)
// 	public List<StockInInventory> stockInventoryItemById(Long id);
}
