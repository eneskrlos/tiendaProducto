package com.example.demo1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.model.Usuario;
import com.example.demo1.response.UserResponse;
import com.example.demo1.service.UsuarioService;





@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServicio;
	
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> listarUsuario(){
		return usuarioServicio.listUser();
	}
	
	@PostMapping
	public ResponseEntity<UserResponse> crearUsuario(@RequestBody Usuario nuevouser) {
		return usuarioServicio.addUsuario(nuevouser);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Integer id) {
		return usuarioServicio.deleteUsuario(id);
	}
}
