package com.psl.stock.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psl.stock.backend.entities.Admin;
import com.psl.stock.backend.entities.Login;
import com.psl.stock.backend.entities.Response;
import com.psl.stock.backend.entities.User;
import com.psl.stock.backend.services.AdminService;
import com.psl.stock.backend.services.JwtService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/StockInInventory/api/security")
public class HomeController {
	@Autowired
	private com.psl.stock.backend.services.UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/normal")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<String> normalUser() {
		return new ResponseEntity<String>("Hii this api for normal users",HttpStatus.OK);
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> adminUser(){
		System.out.println("admit controller");
		return new ResponseEntity<String>("Hii this api for admin",HttpStatus.OK);
	}
	
	@GetMapping("/admin/getAllData")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<User>> getAllData(){
		List<User> allData = this.userService.getAllData();
		return new ResponseEntity<List<User>>(allData,HttpStatus.OK);
	}
	
	@GetMapping("/getValue")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> getvalue(){
		System.out.println("admit controller");
		return new ResponseEntity<String>("Hii this api for admin for value get",HttpStatus.OK);
	}
	
//	@GetMapping("/new")
//	public ResponseEntity<String> user(){
////		User savaAll = this.userService.savaAll(user);
//		return new ResponseEntity<String>("i am nitish kumar",HttpStatus.OK);
//	}
	@PostMapping("/updatenew")
	public ResponseEntity<Response> addData(@RequestBody User user){
		System.out.println("THIS IS USER POST DATA IN DATABASE");
		User findEmailpassword = this.userService.findEmail(user.getEmail());
		if(findEmailpassword ==null) {
			
			User savaAll = this.userService.savaAll(user);
			savaAll.setRole("ROLE_USER");
			int id=savaAll.getId();
			User saveById = this.userService.saveById(id, user);
			
			Response tokenResponse=new Response("Data inserted Successfully");
			
			return new ResponseEntity<Response>(tokenResponse,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Response>(new Response("user already exist"),HttpStatus.OK);
		}
		
		
		
	}
	
	@PostMapping("/addAdmin")
	public ResponseEntity<Response> addData(@RequestBody Admin admin){
		System.out.println("THIS IS USER POST DATA IN DATABASE");
		Admin findEmailpassword = this.adminService.findEmailpassword(admin.getEmail(),admin.getPhone());
		if(findEmailpassword ==null) {
			
			Admin savaAll = this.adminService.savaAll(admin);
			savaAll.setRole("ROLE_ADMIN");
			int id=savaAll.getId();
			Admin saveById = this.adminService.saveById(id, admin);
			
			Response tokenResponse=new Response("Data inserted Successfully");
			
			return new ResponseEntity<Response>(tokenResponse,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Response>(new Response("admin already exist"),HttpStatus.OK);
		}
		
		
		
	}
	
	@GetMapping("/public")
	public ResponseEntity<String> publicUser(){
//		User savaAll = this.userService.savaAll(user);
		return new ResponseEntity<String>("hii this is not secure",HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<Response> generateToken(@RequestBody Login login) {
		System.out.println("generate token");
		User findname = this.userService.findname(login.getUsername());
		String role=findname.getRole();
		if(findname !=null) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
			if(authenticate.isAuthenticated()) {
				
				 String generateToken = jwtService.generateToken(login.getUsername());
				 System.out.println("token return :"+generateToken);
				 return new ResponseEntity<Response>((new Response("login Successfully  "+ generateToken)),HttpStatus.OK);
			}else {
				Response tokenResponse=new Response();
				System.out.println("user not found");
				tokenResponse.setMessage("invalid user id and password");
				
				throw new UsernameNotFoundException(tokenResponse.getMessage());
			}
		}
		else {
			return new ResponseEntity<Response>(new Response("user not Exist"),HttpStatus.OK);
		}
			
		
	}
	@PostMapping("/resetPassword")
	public ResponseEntity<Response> updatePassword(@RequestParam("email")String email,@RequestParam("password")String password){
		User findEmailpassword = this.userService.findEmail(email);
		System.out.println(findEmailpassword);
		
		if(findEmailpassword !=null) {
			findEmailpassword.setPassword(password);
			User savaAll = this.userService.savaAll(findEmailpassword);
			return new ResponseEntity<Response>(new Response("password updated successfully"),HttpStatus.OK);
			
		}
		else {
		return	new ResponseEntity<Response>(new Response("Bad credentials"),HttpStatus.OK);
		}
		
	}
	
	

}
