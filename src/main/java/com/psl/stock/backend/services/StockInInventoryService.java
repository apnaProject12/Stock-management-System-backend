package com.psl.stock.backend.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.psl.stock.backend.entities.StockInInventory;
import com.psl.stock.backend.repositories.StockInInventoryRepo;
@Service
public class StockInInventoryService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private StockInInventoryRepo stockInInventoryRepo;

	
	public StockInInventory addOrUpdate(StockInInventory stockInInventory) {
		return stockInInventoryRepo.save(stockInInventory);
	}
	
	public boolean deleteById(Long id) {
		stockInInventoryRepo.deleteById(id);
		return true;
		
	}
	
	public List<StockInInventory> getAll(){
		return stockInInventoryRepo.findAll();
	}
	
	public StockInInventory getById(Long id) {
		return stockInInventoryRepo.findById(id).get();
	
	}
	
	public List<Map<String,Object>> getProductByInventoryId(Long id){
		
		Map<String,Object> _params=new HashMap<>();
		_params.put("id",id);
		return jdbcTemplate.queryForList("SELECT * FROM inventory_item p JOIN `inventory_in` i ON p.`stock_id`=i.`stock_id` WHERE i.stock_id=:id",_params);
	}
	
	public List<StockInInventory> getItemById(Long id){
	   return	this.stockInInventoryRepo.stockInventoryItemById(id);
	}
	
//	public InventoryInResponse getAllInventoryInPagination(Integer pageNumber, Integer pageSize) {
//		Pageable p = PageRequest.of(pageNumber, pageSize);
//		Page<StockInInventory> pageVisitor = this.stockInInventoryRepo.findAll(p);
//		List<StockInInventory> allInventortIn = pageVisitor.getContent();
//		List<StockInInventory> stcockInInventories = allInventortIn.stream()
//				.map(stock->this.stockInInventory(stock))
//				.collect(Collectors.toList());
//		InventoryInResponse visitorResponse = new InventoryInResponse();
//		return InventoryInResponse;
//	}
	
}
