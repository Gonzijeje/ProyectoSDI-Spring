package com.uniovi.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Friendship;

public interface FriendshipRepository extends CrudRepository<Friendship, Long>{
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Friendship where user.id=?1 or friend.id=?1")
	void deleteFriendshipByUser(Long id);


}
