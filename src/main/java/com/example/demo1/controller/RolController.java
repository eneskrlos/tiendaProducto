package com.example.demo1.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.model.Rol;
import com.example.demo1.request.RequestObject;
import com.example.demo1.response.RolResponse;
import com.example.demo1.service.RolServicie;





@RestController
@RequestMapping("roles")
public class RolController {

	@Autowired
	private RolServicie rolservi;
	
	@PostMapping
	public ResponseEntity<RolResponse> addRol(@RequestBody Rol nuevorol){
		return rolservi.addRol(nuevorol);
	}
	
	@PostMapping(path = "/assign")
	public ResponseEntity<String> asignarRol(@RequestBody RequestObject obj ){
		return rolservi.asignarRol(obj.getIduser(), obj.getIdRol());
	}
}
