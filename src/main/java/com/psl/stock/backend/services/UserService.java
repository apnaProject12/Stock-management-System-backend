package com.psl.stock.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.User;
import com.psl.stock.backend.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	public User savaAll(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User save = this.userRepository.save(user);
		System.out.println("user service class start");
		System.out.println("user service class save All"+save);
		
		return save;
	}
	
	
	public Optional<User> getByid(int id) {
		System.out.println("this is get By id");
		return this.userRepository.findById(id);
	}
	public User saveById(int id,User user) {
		System.out.println("this is save By id");
		user.setId(id);
	return	this.userRepository.save(user);
	
	}
	public List<User> getAllData() {
		List<User> findAll = this.userRepository.findAll();
		System.out.println("user service");
		return findAll;
	}
	
	public User findEmail(String email) {
		System.out.println("email class called");
		 User findByemailAndpassword = this.userRepository.findByemailAndpassword(email);
		 System.out.println("return data in service class :"+findByemailAndpassword);
		 return findByemailAndpassword;
	}
	
	public User findname(String name) {
		User findByName = this.userRepository.findByName(name);
		return findByName;
	}
	
	public User findBynamepassword(String name,String password) {
		System.out.println("password "+password);
	String encode = passwordEncoder.encode(password);
	System.out.println("encode" +encode);
	User userpassword1 = this.userRepository.userpassword1(name, encode);
	System.out.println(userpassword1);
	return userpassword1;
	}
	public List<User> getAllUser() {
		return this.userRepository.findAll();
	}
	
}
