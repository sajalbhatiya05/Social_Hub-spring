package com.bhatiya.service;

import java.util.List;

import com.bhatiya.models.Post;

public interface PostService {
	
	Post creatNewPost(Post post , Integer userId ) throws Exception;
	
	String deletePost( Integer postId , Integer userId ) throws Exception;
	
	List<Post> findPostByUserId( Integer UserId );
	
	Post findPostById( Integer postId ) throws Exception;
	
	List<Post> fintAllPost();
	
	Post savedPost ( Integer postId , Integer userId) throws Exception;
	
	Post likePost ( Integer postId , Integer userId ) throws Exception; 
	
	
}
