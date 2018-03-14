package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Peticion;

public interface PeticionAmistadRepository extends CrudRepository<Peticion, Long>{
	
	@Query("SELECT r FROM Peticion r WHERE r.userRecibe.email =?1")
	Page<Peticion> findRequestByUserEnvia(Pageable pageable, String email);

}
