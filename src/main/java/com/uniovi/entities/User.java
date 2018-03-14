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
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;

	@ManyToMany(cascade = {CascadeType.ALL })
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "friend_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> friends = new HashSet<User>();

	//private Set<PeticionAmistad> friendRequests = new HashSet<PeticionAmistad>();
	
	private boolean recibida;

	public User(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.recibida=false;
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

	public Set<User> getFriends() {
		return friends;
	}
	
	public void setFriends(Set<User> amigos) {
		this.friends = amigos;
	}
	
	/*public Set<PeticionAmistad> getFriendRequests() {
		return friendRequests;
	}
	
	public void setFriendRequests(Set<PeticionAmistad> peticiones) {
		this.friendRequests = peticiones;
	}*/

	public boolean isRecibida() {
		return recibida;
	}

	public void setRecibida(boolean recibida) {
		this.recibida = recibida;
	}
	

}
