package com.example.demo1.response;

import java.io.Serializable;


public class UserResponse implements Serializable   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private String username;
	
	private boolean estado;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}	
}
