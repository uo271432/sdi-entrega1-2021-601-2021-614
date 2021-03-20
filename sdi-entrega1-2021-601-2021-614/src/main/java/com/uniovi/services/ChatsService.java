package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Chat;
import com.uniovi.entities.User;
import com.uniovi.repositories.ChatsRepository;

@Service
public class ChatsService {

	@Autowired
	private ChatsRepository chatsRepository;
	
	public List<Chat> getChatsAsSellerByUser(User seller) {
		List<Chat> chats = new ArrayList<Chat>();
		chatsRepository.findAllByUserAsSeller(seller).forEach(chats::add);
		return chats;
	}
	
	public List<Chat> getChatsAsInterestedByUser(User interested) {
		List<Chat> chats = new ArrayList<Chat>();
		chatsRepository.findAllByUserAsInterested(interested).forEach(chats::add);
		return chats;
	}
	
	public Chat getChatById(Long id) {
		return chatsRepository.findById(id).get();
	}
	
}