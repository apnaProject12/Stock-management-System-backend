package com.psl.stock.backend.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public Page<StockInInventory> getAll(int pageNo,int pageSize,String field,String sortDir){
		System.out.println(pageNo);
		System.out.println(pageSize);
		System.out.println(field);
		System.out.println(sortDir);
Sort sort=null;
        if (sortDir.equalsIgnoreCase("asc")) {
		sort=Sort.by(field).ascending();
		}
		else{
		sort=	Sort.by(field).descending();
		}

	Pageable p=	PageRequest.of(pageNo, pageSize,sort) ;
		
		return  stockInInventoryRepo.findAll(p);
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
	

	
}
