 package com.bhatiya.service;

import java.util.List;

import com.bhatiya.models.Message;
import com.bhatiya.models.User;

public interface MessageService {
	
	public Message createMessage( User user, Integer chatId, Message req ) throws Exception;
	
	public List<Message> findChatsMessages( Integer chatId ) throws Exception;
	
}
