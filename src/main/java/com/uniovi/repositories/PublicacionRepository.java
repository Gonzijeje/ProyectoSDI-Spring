package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Publicacion;

public interface PublicacionRepository extends CrudRepository<Publicacion, Long>{
	
	@Query("SELECT r FROM Publicacion r WHERE r.user.email =?1")
	Page<Publicacion> findPublicacionByUser(Pageable pageable, String email);

}
