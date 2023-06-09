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
import org.springframework.web.bind.annotation.GetMapping;
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
import com.psl.stock.backend.entities.loginResponse;
import com.psl.stock.backend.services.AdminService;
import com.psl.stock.backend.services.JwtService;
import com.psl.stock.backend.services.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/StockInInventory/api/security")
public class HomeController {
	@Autowired
	private UserService userService;
	
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
        this.userService.savaAll(user);
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
        this.adminService.savaAll(admin);
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
	public ResponseEntity<?> generateToken(@RequestBody Login login) {
		System.out.println("generate token");
		User findname = this.userService.findname(login.getUsername());
		loginResponse loginresponse=new loginResponse();
		loginresponse.setUser(findname.getName());
		loginresponse.setRole(findname.getRole());
		loginresponse.setMessage("login Successfully");
	
		if(findname !=null) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
			if(authenticate.isAuthenticated()) {
				
				 String generateToken = jwtService.generateToken(login.getUsername());
				 System.out.println("token return :"+generateToken);
				 loginresponse.setToken(generateToken);
				 return new ResponseEntity<loginResponse>(loginresponse,HttpStatus.OK);
			}else {
				Response tokenResponse=new Response();
				System.out.println("user not found");
				tokenResponse.setMessage("invalid user id and password");
				System.out.println("this is home");
				
				throw new UsernameNotFoundException(tokenResponse.getMessage());
			}
		}
		else {
			return new ResponseEntity<Response>(new Response("user not Exist"),HttpStatus.OK);
		}
			
		
	}
	@GetMapping("/resetPassword")
	public ResponseEntity<Response> updatePassword(@RequestParam("email")String email,@RequestParam("phone")Long phone ,@RequestParam("password")String password) throws Exception{
		return new ResponseEntity<Response>(this.userService.forgetPassword(password, phone, email),HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<List<User>>(this.userService.getAllUser(),HttpStatus.OK);
	}
	
	

}
