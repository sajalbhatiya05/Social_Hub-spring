package com.bhatiya.service;

import java.util.List;

import com.bhatiya.models.Chat;
import com.bhatiya.models.User;

public interface ChatService {

	public Chat createChat( User reqUser, User user2 );
	
	public Chat findChatById( Integer chatId ) throws Exception;
	
	public List<Chat> findUsersChat( Integer userId );
	
	
}
