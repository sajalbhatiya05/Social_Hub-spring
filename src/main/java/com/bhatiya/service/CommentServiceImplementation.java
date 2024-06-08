package com.bhatiya.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhatiya.models.Comment;
import com.bhatiya.models.Post;
import com.bhatiya.models.User;
import com.bhatiya.repository.CommentRepository;
import com.bhatiya.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService {
	
	 @Autowired
	 private UserService userService;
	
	
	@Autowired
	private PostService postService;
	
	
	@Autowired
	private CommentRepository commentRepository; 
	
	@Autowired
	private PostRepository postRepository;
	
	
	

	@Override
	public Comment createCommment(Comment comment, Integer postId, Integer userId) throws Exception {
		
		User  user = userService.findUserById(userId);
		 
		Post post = postService.findPostById(postId);
		
		comment.setUser(user);
		
		comment.setContent(comment.getContent());
		
		comment.setCreatedAt(LocalDateTime.now());
		 
		Comment savedComment = commentRepository.save(comment); 
		
		post.getComments().add(savedComment); 
		
		postRepository.save(post);
		
		return savedComment ;
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {

		Comment comment = findCommentById(commentId);
		
		User user = userService.findUserById(userId); 
		
		if ( !comment.getLiked().contains(user) ) {
			comment.getLiked().add(user);
			
		}else {
			comment.getLiked().remove(user);
		}
		
		return commentRepository.save(comment);
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {


		Optional<Comment> opt =commentRepository.findById(commentId);
		
		if ( opt.isEmpty() ) {
			throw new Exception("commment nor exist");
		}
		
		
		return opt.get();
	}

}
