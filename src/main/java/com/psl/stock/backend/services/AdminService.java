package com.psl.stock.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.Admin;
import com.psl.stock.backend.repositories.AdminRepository;
//import com.springSecurity.userRepository.UserRepository;
@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public Admin savaAll(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		Admin save = this.adminRepository.save(admin);
		System.out.println("user service class start");
		System.out.println("user service class save All"+save);
		
		return save;
	}
	
	
	public Optional<Admin> getByid(int id) {
		System.out.println("this is get By id");
		return this.adminRepository.findById(id);
	}
	public Admin saveById(int id,Admin admin) {
		System.out.println("this is save By id");
		admin.setId(id);
	return	this.adminRepository.save(admin);
	
	}
	public List<Admin> getAllData() {
		List<Admin> findAll = this.adminRepository.findAll();
		System.out.println("user service");
		return findAll;
	}
	
	public Admin findEmailpassword(String email,long phone) {
		 Admin findByemailAndpassword = this.adminRepository.findByemailAndpassword(email, phone);
		 System.out.println("return data in service class :"+findByemailAndpassword);
		 return findByemailAndpassword;
	}
	
	public Admin findname(String name) {
		Admin findByName = this.adminRepository.findByName(name);
		return findByName;
	}
	
	public Admin findBynamepassword(String name,String password) {
		System.out.println("password "+password);
	String encode = passwordEncoder.encode(password);
	System.out.println("encode" +encode);
	Admin userpassword1 = this.adminRepository.userpassword1(name, encode);
	System.out.println(userpassword1);
	return userpassword1;
	}

}
