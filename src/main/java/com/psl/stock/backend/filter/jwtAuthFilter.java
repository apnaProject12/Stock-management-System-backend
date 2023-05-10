package com.psl.stock.backend.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.psl.stock.backend.config.CustomUserDetailService;
import com.psl.stock.backend.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class jwtAuthFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("jwt Auth filter class start :");
		String authHeader=request.getHeader("Authorization");
		System.out.println("doFilterInternal class Auth Header "+authHeader);
		String token=null;
		String username=null;
		if(authHeader !=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
		username=jwtService.extractUsername(token);
		System.out.println("user name in auth filter :"+username);
		}
		if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
			System.out.println(" user details :"+userDetails);
			
			if(jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				System.out.println("auth token:"+authToken);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
		}
		filterChain.doFilter(request, response);
		System.out.println("jwt Auth filter class end");
		}
		
	}


