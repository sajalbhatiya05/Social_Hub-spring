package com.bhatiya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import com.bhatiya.models.Post;
import com.bhatiya.models.User;
import com.bhatiya.response.ApiResponse;
import com.bhatiya.service.PostService;
import com.bhatiya.service.UserService;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost ( @RequestBody Post post , @RequestHeader("Authorization") String jwt) throws Exception{
		
		User user =  userService.findUserByJwt(jwt);
		
		Post createdPost  = postService.creatNewPost(post, user.getId());
		
		return new ResponseEntity<>(createdPost , HttpStatus.OK) ;
	}
	
	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<ApiResponse> deletedPost( @PathVariable Integer postId ,@RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user =  userService.findUserByJwt(jwt);
		
		String message = postService.deletePost(postId, user.getId()); 
		
		ApiResponse res = new ApiResponse(message , true );
		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK ) ;
	} 
	
	
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId ) throws Exception{
		
		Post post = postService.findPostById(postId); 
		
		return new ResponseEntity<Post>(post , HttpStatus.OK );
	}
	
	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost( @PathVariable Integer userId ){
		
		
		List<Post> posts = postService.findPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts , HttpStatus.ACCEPTED );
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		
		List<Post> posts = postService.fintAllPost(); 
		
		return new ResponseEntity<List<Post>>(posts , HttpStatus.OK );
	}
	
	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId , @RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user =  userService.findUserByJwt(jwt);
		
		Post savedPost = postService.savedPost(postId, user.getId());
	
		return new ResponseEntity<Post>(savedPost , HttpStatus.ACCEPTED);
	} 
	
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId , @RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user =  userService.findUserByJwt(jwt);
		
		Post savedPost = postService.likePost(postId, user.getId());
		
		return new ResponseEntity<Post>(savedPost , HttpStatus.OK);
	}  

}
