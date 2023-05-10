 package com.psl.stock.backend.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.User;
import com.psl.stock.backend.repositories.UserRepository;
@Component
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepository  repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("start customUserService");
		// TODO Auto-generated method stub
		User user = this.repository.findByName(username);
		System.out.println("user details"+user);
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		
		if(user==null) {
			throw new UsernameNotFoundException("user not found !!");
			
		}else {
			CustomUserDetail customUserDetail= new CustomUserDetail(user); 
			System.out.println("custom user details :"+customUserDetail);
			System.out.println("custom user service class end");
			return customUserDetail;
		}
//    return   user.map(CustomUserDetail::new )
//       .orElseThrow(()->new UsernameNotFoundException("user not found"+username));
	


}
}
