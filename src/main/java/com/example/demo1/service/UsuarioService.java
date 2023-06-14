package com.example.demo1.service;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo1.dao.IUsuarioDAO;
import com.example.demo1.model.Usuario;


import com.example.demo1.response.UserResponse;
import com.example.demo1.utilencrypt.EncryptService;




@Service
public class UsuarioService   {

	@Autowired
	private IUsuarioDAO userdao;
	
	@Bean
	EncryptService encode() {
		EncryptService es = new EncryptService();
		return es;
	}
	
	public ResponseEntity<List<UserResponse>> listUser(){
		try {
			List<Usuario> listiuser = userdao.findAll();
			List<UserResponse> response = new ArrayList<>();
			for (int i = 0; i < listiuser.size(); i++) {
				UserResponse ur = null;
				response.add(mappearDatos(listiuser.get(i), ur));
			}
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<UserResponse> addUsuario(Usuario usernew) {
		try {
			Usuario aux = usernew;
			aux.setEstado(true);
			aux.setPassword(encode().encryptarPassword(usernew.getPassword()));
			Usuario creado =  userdao.save(aux);
			UserResponse ur = null;
			return ResponseEntity.ok(mappearDatos(creado, ur));
		} catch (Exception e) {
			 return ResponseEntity.badRequest().build();
		}
		
	}
	
	public ResponseEntity<String> deleteUsuario(int id) {
		try {
			Optional<Usuario> userfind = userdao.findById(id);
			if(userfind.isPresent()) {
				Usuario aux = userfind.get();
				aux.setEstado(false);
				userdao.save(aux);
				return ResponseEntity.ok("Ok");
			}else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			 return ResponseEntity.badRequest().build();
		}
		
	}	
	
	private UserResponse mappearDatos(Usuario contenedor, UserResponse index) {
		index.setId(contenedor.getId());
		index.setName(contenedor.getName());
		index.setUsername(contenedor.getUsername());
		index.setEstado(index.isEstado());
		return index;
	}	
	
}
