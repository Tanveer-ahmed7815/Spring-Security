package com.javatechie.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javatechie.api.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			//here we create our own user details service and we need to fetch
			//the user object based on the incoming request which will be the username
			//and password so this login will be in service
			auth.userDetailsService(customUserDetailsService);
		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
		
		@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManager();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//for this particular request permit and any other request authenticate
			http.csrf().disable().authorizeRequests().antMatchers("/autheticate").permitAll().anyRequest().authenticated();
		}
}
