package com.example.demo1.response;

import java.io.Serializable;

public class RolResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String role_name;
	
	

	public RolResponse(int id, String role_name) {
		super();
		this.id = id;
		this.role_name = role_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	

}
