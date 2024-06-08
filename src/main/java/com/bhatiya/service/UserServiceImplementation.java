package com.bhatiya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhatiya.configuration.JwtProvider;
import com.bhatiya.models.User;
import com.bhatiya.repository.UserRepository;



@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository  userRepository;

	@Override
	public User registerUser(User user) {
		
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		
		
		// inserting user in database
		User savedUser = userRepository.save(newUser);
		
		return savedUser;
		  
	}

	@Override
	public User findUserById(Integer userId) throws Exception  {
		
		Optional<User> user = userRepository.findById(userId);
		
		if ( user.isPresent())
			return user.get();
		
		
		throw new Exception( " user doesn't exist with userId " + userId );
	}

	
	@Override
	public User findUserByEmail(String email) {
		

		User user = userRepository.findByEmail(email);
		 
		return user;
	}

	@Override
	public User followUser(Integer reqUserId , Integer userId2) throws Exception {
		
		User reqUser = findUserById(reqUserId);
		
		User user2 = findUserById(userId2);
		  
		
		//user1 aur user2 ko update kiya h isliye inhe save bhi karna padega 
		user2.getFollowers().add(reqUser.getId());
		
		reqUser.getFollowings().add(user2.getId());
		
		//save logic 
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		 
		return reqUser;
	}

	@Override
	public User updateUser(User user , Integer userId) throws Exception {

		Optional<User> user1 = userRepository.findById(userId);
		
		if ( user1.isEmpty()) {
			 throw new Exception ( " user doesn't exist with userId " + userId );
		}
		
		
		User oldUser = user1.get();
		
		if( user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		
		if( user.getLastName() != null ) {
			oldUser.setLastName(user.getLastName()); 
		}
		
		if( user.getEmail() != null ) {
			oldUser .setEmail(user.getEmail()); 
		}
		
		User updatedUser = userRepository.save(oldUser);
		 
		return updatedUser ;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query); 
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email =  JwtProvider.getEmailFromJwtToken( jwt);
		
		User user = userRepository.findByEmail(email);
		
		return user;
	}

}  
