package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Peticion;
import com.uniovi.repositories.PeticionAmistadRepository;

@Service
public class PeticionAmistadService {
	
	@Autowired
	private PeticionAmistadRepository peticionRepository;
	
	@PostConstruct
	public void init() {
	}
	
	public void addPeticion(Peticion peticion) {
		peticionRepository.save(peticion);
	}
	
	public Peticion getPeticion(Long id) {
		return peticionRepository.findOne(id);
	}
	
	public Page<Peticion> getFriendRequestsByUserEnvia(Pageable pageable, String email){
		return peticionRepository.findRequestByUserEnvia(pageable, email);
	}

}
