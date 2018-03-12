package com.uniovi.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{

	User findByEmail(String email);
	
	Page<User> findAll(Pageable pageable);
	
}
