package com.bhatiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhatiya.configuration.JwtProvider;
import com.bhatiya.models.User;
import com.bhatiya.repository.UserRepository;
import com.bhatiya.request.LoginRequest;
import com.bhatiya.response.AuthResponse;
//import com.bhatiya.service.UserService;
import com.bhatiya.service.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthContoller {
	
//	@Autowired
//	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	  
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetails;
	
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user ) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if( isExist != null ) {
			throw new Exception("This Email is already registered");
		}
		
		User newUser = new User();
		 
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		// inserting user in database
		User savedUser = userRepository.save(newUser);
		 
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword() );
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse response = new AuthResponse( token , "Register Successfully. ");
		
		return response;
		
	} 
	
	@PostMapping("/signin")
	public AuthResponse signin ( @RequestBody LoginRequest loginRequest ) {
		
		Authentication authentication =  authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse response = new AuthResponse( token , "Login Successfully.  ");
		
		return response;
	}


	private Authentication authenticate(String email, String password) {
		
		UserDetails  userDetails = customerUserDetails.loadUserByUsername(email);
		
		if ( userDetails == null ) {
			throw new BadCredentialsException(" invalid username ");
		}
		
		if( !passwordEncoder.matches(password, userDetails.getPassword() ) ) {
			throw new BadCredentialsException("pssword not matched ");
		}
		 																												
		
		return new   UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities() );
	}

	
}
