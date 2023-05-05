package com.psl.stock.backend.services;

import java.util.List;
import java.util.stream.Collectors;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<InventoryOut> getOutList(int pageNo, int pageSize, String field, String sortDir) {

        Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(field).ascending();
		} else {
			sort = Sort.by(field).descending();
		}

		Pageable p = PageRequest.of(pageNo, pageSize, sort);

		return this.repo.findAll(p);
    }

    public   List<InventoryOutItem> findOutItemByFrom(Long id) {
      return this.repo.findById(id).get().getInventoryOutItem();
    }
    public void deleteAllOut(long id) throws Exception {
        this.repo.findById(id).orElseThrow(()->new Exception("id not found "+id));
        this.repo.deleteById(id);
   
      
    }
    public InventoryOut findInventoryOutById(long id) throws Exception{
    return  this.repo.findById(id).orElseThrow(()-> new Exception("id not found "+id));
    }

    
}
