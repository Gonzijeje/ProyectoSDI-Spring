package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
	
	@Id @GeneratedValue private long id;
	@Column(unique = true)
	private String email;
	private String name;

	private String password;
	@Transient
	private String passwordConfirm;

	@OneToMany(mappedBy = "userEnvia", cascade = CascadeType.ALL)
	private Set<Peticion> peticionesEnviadas = new HashSet<Peticion>();
	
	@OneToMany(mappedBy = "userRecibe", cascade = CascadeType.ALL)
	private Set<Peticion> peticionesRecibidas = new HashSet<Peticion>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Publicacion> publicaciones = new HashSet<Publicacion>();

	@ManyToMany(cascade = {CascadeType.ALL })
	@JoinTable(name = "amigos", joinColumns = @JoinColumn(name = "amigo_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> amigos = new HashSet<User>();
	
	private String role;
	

	public User(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Peticion> getPeticionesEnviadas() {
		return peticionesEnviadas;
	}

	public void setPeticionesEnviadas(Set<Peticion> peticionesEnviadas) {
		this.peticionesEnviadas = peticionesEnviadas;
	}

	public Set<Peticion> getPeticionesRecibidas() {
		return peticionesRecibidas;
	}

	public void setPeticionesRecibidas(Set<Peticion> peticionesRecibidas) {
		this.peticionesRecibidas = peticionesRecibidas;
	}

	public Set<User> getAmigos() {
		return amigos;
	}

	public void setAmigos(Set<User> amigos) {
		this.amigos = amigos;
	}

	public Set<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(Set<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
