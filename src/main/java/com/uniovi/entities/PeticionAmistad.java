package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*@Entity
public class PeticionAmistad {
	
	@Id @GeneratedValue private long id;
	
	private User userEnvia;
	private User userRecibe;
	private boolean aceptada; //True(aceptada)
	
	public PeticionAmistad(User envia,User recibe,boolean estado) {
		super();
		this.userEnvia=envia;
		this.userRecibe=recibe;
		this.aceptada=estado;
	}

	public PeticionAmistad() {	
	}	

	public User getUserEnvia() {
		return userEnvia;
	}

	public void setUserEnvia(User userEnvia) {
		this.userEnvia = userEnvia;
	}

	public User getUserRecibe() {
		return userRecibe;
	}

	public void setUserRecibe(User userRecibe) {
		this.userRecibe = userRecibe;
	}

	public boolean isAceptada() {
		return aceptada;
	}

	public void setAceptada(boolean estado) {
		this.aceptada = estado;
	}

	public void acceptFriendRequest() {
		userRecibe.getFriends().add(userEnvia);
		userRecibe.getFriendRequests().remove(this);
	}

}*/
