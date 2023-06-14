package com.example.demo1.request;

import org.springframework.web.context.annotation.RequestScope;

@RequestScope
public class RequestObject {

	private Integer iduser;
	private Integer idRol;
	
	public Integer getIduser() {
		return iduser;
	}
	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	
	
}
