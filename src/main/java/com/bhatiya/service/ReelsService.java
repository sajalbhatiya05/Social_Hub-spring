package com.bhatiya.service;

import java.util.List;

import com.bhatiya.models.Reels;
import com.bhatiya.models.User;

public interface ReelsService {

	public Reels createReel( Reels reels , User user );
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReel( Integer userId ) throws Exception;
	
	
	
	
	
}
