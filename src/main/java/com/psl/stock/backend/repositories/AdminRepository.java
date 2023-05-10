package com.psl.stock.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.psl.stock.backend.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

public Admin findByName(String name);
	
//	@Query(value="SELECT * FROM user where email LIKE (%:email%) and phone LIKE (%:phone%)",nativeQuery = true)
	@Query(value="SELECT * FROM Admin where email=:email and phone=:phone ",nativeQuery = true)
	public Admin findByemailAndpassword(String email,long phone);
	
	@Query(value="SELECT * FROM user where name=:name and password=:password ",nativeQuery = true)
	public Admin userpassword1(String name,String password);
	
	public Admin findByEmail(String email) ;
	
	

}
