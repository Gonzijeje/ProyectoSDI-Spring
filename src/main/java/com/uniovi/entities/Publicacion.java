package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Publicacion {
	
	@Id @GeneratedValue public long id;
	
	private String titulo;
	private String descripcion;
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Publicacion(String titulo, String descripcion, Date fecha, User user) {
		super();
		this.titulo=titulo;
		this.descripcion=descripcion;
		this.fecha=fecha;
		this.user=user;
	}
	
	public Publicacion(){	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
