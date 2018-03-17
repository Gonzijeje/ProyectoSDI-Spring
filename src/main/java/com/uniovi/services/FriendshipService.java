package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Friendship;
import com.uniovi.repositories.FriendshipRepository;

@Service
public class FriendshipService {
	
	@Autowired
	private FriendshipRepository friendshipRepository;
	
	@PostConstruct
	public void init() {
	}
	
	public void addFriendship(Friendship fr) {
		friendshipRepository.save(fr);
	}
	
	public void deleteFrienshipByUser(Long id) {
		friendshipRepository.deleteFriendshipByUser(id);
	}

}
