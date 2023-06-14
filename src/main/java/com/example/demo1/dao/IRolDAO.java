package com.example.demo1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.model.Rol;



public interface IRolDAO extends JpaRepository<Rol, Integer> {

}
