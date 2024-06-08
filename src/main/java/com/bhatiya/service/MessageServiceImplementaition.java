package com.bhatiya.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhatiya.models.Chat;
import com.bhatiya.models.Message;
import com.bhatiya.models.User;
import com.bhatiya.repository.ChatRepository;
import com.bhatiya.repository.MessageRepository;

@Service
public class MessageServiceImplementaition implements MessageService{
	
	
	@Autowired  
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Message createMessage(User user, Integer chatId, Message req ) throws Exception {
		
		Message  message = new Message();
		
		Chat chat = chatService.findChatById(chatId);
		
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		
		Message savedMessage = messageRepository.save(message);
		
		chat.getMessages().add(savedMessage);
		
		chatRepository.save(chat); 
		
		return savedMessage  ;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		
		Chat chat = chatService.findChatById(chatId);
		
		return messageRepository.findByChatId(chatId);
	}

	 
	
	
	
	
}
