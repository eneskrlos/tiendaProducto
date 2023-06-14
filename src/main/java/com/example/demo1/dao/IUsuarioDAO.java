package com.example.demo1.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {

	Usuario findByUsername(String username);
}
