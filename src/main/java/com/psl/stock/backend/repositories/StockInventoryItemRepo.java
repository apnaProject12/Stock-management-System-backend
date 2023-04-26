package com.psl.stock.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psl.stock.backend.entities.StockInventoryItem;

@Repository
public interface StockInventoryItemRepo extends JpaRepository<StockInventoryItem, Long> {

}
