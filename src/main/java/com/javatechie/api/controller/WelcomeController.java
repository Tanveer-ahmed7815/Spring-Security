package com.javatechie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.api.entity.AuthRequest;
import com.javatechie.api.util.JwtUtil;

@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager autheticationManager;

	@GetMapping("/")
	public String welcome() {

		return "welcome to java techie";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {

		// this will autheticate username and password
		// if authentication is successful then only it'll generate the token
		try {
			autheticationManager.authenticate(

					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("invalid username and password");
		}

		return jwtUtil.generateToken(authRequest.getUserName());
	}
}
