package com.psl.stock.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.psl.stock.backend.entities.StockInventoryItem;
import com.psl.stock.backend.repositories.StockInventoryItemRepo;

@Service
public class StockInventoryItemService {

	@Autowired
	private StockInventoryItemRepo stockInventoryItemRepo;

	public StockInventoryItem addOrUpdate(StockInventoryItem stockInventoryItem) {
		return stockInventoryItemRepo.save(stockInventoryItem);
	}

	public boolean deleteById(Long id) {
		stockInventoryItemRepo.deleteById(id);
		return true;
	}

	public List<StockInventoryItem> getAll() {
		return stockInventoryItemRepo.findAll();
	}

	public StockInventoryItem getById(Long id) {
		return stockInventoryItemRepo.findById(id).get();
	}
}
