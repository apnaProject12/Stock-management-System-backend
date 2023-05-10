  package com.psl.stock.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.psl.stock.backend.filter.jwtAuthFilter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{
	@Autowired
	private jwtAuthFilter authFilter;
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("security config clsss stasrt");
		System.out.println("this is password encoder");
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("this is user service file in securityconfig");
	CustomUserDetailService customUserDetailService=new CustomUserDetailService();
	System.out.println("return custom user detail service class return in security config :"+customUserDetailService);
	return customUserDetailService;
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		System.out.println("this is csecurity config class start");
	DefaultSecurityFilterChain build = httpSecurity.
		csrf().
		disable().
		authorizeHttpRequests().
		requestMatchers("/api/security/authenticate","/api/security/updatenew","/api/security/addAdmin","/api/security/download/excel","/api/security/OtpsendwithEmail","/api/security/resetPassword","/api/security/Otpsend").
		permitAll().
		anyRequest().
		authenticated().
		and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class).build();
	System.out.println("security filter chail method return :"+build);
	
	return build;
		

	}
	
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		System.out.println("this is config file");
//		return	httpSecurity.
//				csrf().
//				disable().
//				authorizeHttpRequests().
//				requestMatchers("/api/security/authenticate","/api/security/updatenew").
//				permitAll().
//				anyRequest().
//				authenticated().
//				and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.authenticationProvider(authenticationProvider())
//				.addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class).build();
//		
////		System.out.println(httpSecurity.build() );
////		return httpSecurity.build();
//	}
	
	
	
	
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		System.out.println("this is doa authentication file method");
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
            
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		System.out.println("DaoAuthenticationProvider return :"+daoAuthenticationProvider);
		return daoAuthenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		
		 AuthenticationManager authenticationManager = configuration.getAuthenticationManager();
		 System.out.println("athentication Manager return :"+authenticationManager);
		 System.out.println("security congig class end");
		 return authenticationManager;
	}

}
