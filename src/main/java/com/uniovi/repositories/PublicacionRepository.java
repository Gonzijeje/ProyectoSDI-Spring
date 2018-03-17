package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Publicacion;

public interface PublicacionRepository extends CrudRepository<Publicacion, Long>{
	
	@Query("SELECT r FROM Publicacion r WHERE r.user.email =?1")
	List<Publicacion> findPublicacionByUser(String email);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Publicacion where user.id=?1")
	void deletePublicacionByUser(Long id);

}
