package com.psl.stock.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.AddProductEntities;
import com.psl.stock.backend.repositories.AddProductRepo;

@Service
public class AddProductService {

	@Autowired
	private AddProductRepo addProductRepo;

	public AddProductEntities addOrUpdate(AddProductEntities addProductEntities) {
		return addProductRepo.save(addProductEntities);
	}

	public boolean deleteById(Long id) {
		addProductRepo.deleteById(id);
		return true;

	}

	public List<AddProductEntities> getAll() {
		return addProductRepo.findAll();
	}

	public AddProductEntities getById(Long id) {
		return addProductRepo.findById(id).get();

	}

}
