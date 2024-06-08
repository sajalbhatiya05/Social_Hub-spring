package com.bhatiya.service;

import com.bhatiya.models.Comment;

public interface CommentService {

	public Comment createCommment (Comment comment , Integer postId, Integer userId ) throws Exception;
	
	public Comment likeComment( Integer commentId, Integer userId ) throws Exception;
	
	public Comment  findCommentById( Integer commentId ) throws Exception;
	
}
