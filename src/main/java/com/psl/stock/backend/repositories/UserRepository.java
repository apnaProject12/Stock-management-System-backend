package com.psl.stock.backend.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.psl.stock.backend.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByName(String name);
	
//	@Query(value="SELECT * FROM user where email LIKE (%:email%) and phone LIKE (%:phone%)",nativeQuery = true)
	@Query(value="SELECT * FROM user where email=:email",nativeQuery = true)
	public User findByemailAndpassword(String email);
	
	@Query(value="SELECT * FROM user where name=:name and password=:password ",nativeQuery = true)
	public User userpassword1(String name,String password);
	
	public User findByEmail(String email) ;
	
		
	

}
