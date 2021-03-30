package com.javatechie.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.javatechie.api.entity.User;
import com.javatechie.api.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityApplication {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void initUsers() {
		List<User> users = Stream.of(

				new User(101, "javatechie", "password", "javatechie@gmail.com"),
				new User(101, "user1", "password1", "user1@gmail.com"),
				new User(101, "user2", "password2", "user2@gmail.com"),
				new User(101, "user3", "password3", "user3@gmail.com")).collect(Collectors.toList());
				userRepository.saveAll(users); 
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
