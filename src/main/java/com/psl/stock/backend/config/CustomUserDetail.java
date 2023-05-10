 package com.psl.stock.backend.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.psl.stock.backend.entities.User;

public class CustomUserDetail implements UserDetails{
//	private String name;
//	private String password;
//	private String role;
	User user;
	// private List<GrantedAuthority> authorities;
	
	public  CustomUserDetail(User user) {
		System.out.println("custom user details class start");
		this.user=user;
		
//		authorities=Arrays.stream(user.getRole().split()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
				
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
//		System.out.println(authorities);
	SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(user.getRole());
	System.out.println(" get Authority "+simpleGrantedAuthority);
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		System.out.println("get password : "+user.getPassword());
		// TODO Auto-generated method stub
		return user.getPassword();
		
//		return user.getPassword();
	}

	@Override
	public String getUsername() {
		System.out.println("get User name :"+user.getName());
		// TODO Auto-generated method stub
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		
		System.out.println("check Account Expire");
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		System.out.println("customUserDetails class end");
		// TODO Auto-generated method stub
		return true;
	}

}
