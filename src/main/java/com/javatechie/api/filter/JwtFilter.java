package com.javatechie.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.javatechie.api.service.CustomUserDetailsService;
import com.javatechie.api.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationHeader = request.getHeader("Authorization");
		String token =null;
		String userName=null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {

			token = authorizationHeader.substring(7);
			userName=jwtUtil.extractUsername(token);
			
		}
		
		if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		UserDetails userDetails=	customUserDetailsService.loadUserByUsername(userName);
		}

	}

}
