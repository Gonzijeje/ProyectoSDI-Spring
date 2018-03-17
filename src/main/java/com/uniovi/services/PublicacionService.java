package com.uniovi.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Publicacion;
import com.uniovi.repositories.PublicacionRepository;

@Service
public class PublicacionService {
	
	@Autowired
	private PublicacionRepository publicacionRepository;
	
	@PostConstruct
	public void init() {
	}
	
	public void addPublicacion(Publicacion p) {
		publicacionRepository.save(p);
	}
	
	public List<Publicacion> getPublicaciones(String email){
		return publicacionRepository.findPublicacionByUser(email);
	}
	
	public void deletePublicacionByUser(Long id) {
		publicacionRepository.deletePublicacionByUser(id);
	}

}
