 package com.bhatiya.controller;

 
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhatiya.models.User;
import com.bhatiya.repository.UserRepository;
import com.bhatiya.service.UserService;

@RestController
public class UserController {
	
	
	@Autowired
	UserRepository userRepository ;
	
	@Autowired
	UserService  userService;
	
	
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
		// find all the user from userRepository
		List<User> users = userRepository.findAll();
		
		return users; 
	}
	 
 	
	@GetMapping("/api/users/{userId}")
	public User getUsersById(@PathVariable("userId") Integer id) throws Exception {
		
		User user = userService.findUserById(id);
		
		return user;
		
	}
	 
	
	
	@PutMapping("/api/users")
	public User updateUser(@RequestBody User user , @RequestHeader("Authorization") String jwt ) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updateUser( user , reqUser.getId()); 
		
		return updatedUser;
	}
	
//	@DeleteMapping("/users/{userId}")
//	public String deleteUser(@PathVariable ("userId") Integer userId) throws Exception {
//		
//
//		Optional<User> user = userRepository.findById(userId);
//		 
//		if ( user.isEmpty()) {
//			 throw new Exception ( " user doesn't exist with userId " + userId );
//		}
//		
//		userRepository.delete(user.get());
//		
//		return "user deleted successfully with Id " + userId ;
//	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler( @RequestHeader("Authorization") String jwt ,@PathVariable Integer userId2 ) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User user = userService.followUser( reqUser.getId()  , userId2);
		
		return user; 
		
	}
	
	@GetMapping("/api/users/search") 
	public List<User> searchUser(@RequestParam("query") String query ){
		
		List<User> users = userService.searchUser(query);
		
		return users;
		
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt ) {
		
//		String email =  
//		
//		System.out.println("jwt-----" +jwt );
		
		User user = userService.findUserByJwt(jwt);
		
		user.setPassword(null); 
		
		return user;
	}
	
	
}
