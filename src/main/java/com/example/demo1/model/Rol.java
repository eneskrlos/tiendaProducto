package com.example.demo1.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;




@Entity 
@Table(name="rol")
public class Rol {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="role_name")
	private String role_name;
	
	@ManyToMany(mappedBy = "user_rol")
	private List<Usuario> user_rol = new ArrayList<>();
	
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

	

	public List<Usuario> getUser_rol() {
		return user_rol;
	}

	public void setUser_rol(List<Usuario> user_rol) {
		this.user_rol = user_rol;
	}
	
	
	
}
