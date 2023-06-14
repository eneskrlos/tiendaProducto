package com.example.demo1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo1.dao.IRolDAO;
import com.example.demo1.dao.IUsuarioDAO;
import com.example.demo1.model.Rol;
import com.example.demo1.model.Usuario;
import com.example.demo1.response.RolResponse;



@Service
public class RolServicie {

	@Autowired
	private IRolDAO roldao;
	
	@Autowired
	private IUsuarioDAO userdao;
	
	public ResponseEntity<RolResponse> addRol(Rol rol) {
		try {
			Rol aux = rol;
			aux.setRole_name(rol.getRole_name());
			Rol creado =  roldao.save(aux);
			RolResponse rr = null;
			return ResponseEntity.ok().body(mappearDatos(creado,rr));
		} catch (Exception e) {
			 return ResponseEntity.badRequest().build();
		}
		
	}
	
	public ResponseEntity<String> asignarRol(Integer iduser, Integer idrol){
		try {
			Optional<Usuario> userfind = userdao.findById(iduser);
			Optional<Rol> rolfind = roldao.findById(idrol);
			if(userfind.isPresent() || rolfind.isPresent()) {
				
				Usuario u = userfind.get();
				if(u.isEstado() == true) {
					Rol r = rolfind.get();
					u.getUser_rol().add(r);
					userdao.save(u);
				}else {
					return ResponseEntity.ok().body("Error: El usuario no esta activo.");
				}
				return ResponseEntity.ok("Ok");
			}else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
		
	}
	
	private RolResponse mappearDatos(Rol contenedor, RolResponse index) {
		index = new RolResponse(contenedor.getId(), contenedor.getRole_name());
		return index;
	}
	
	
}
