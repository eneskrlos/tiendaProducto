package com.example.demo1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo1.dao.IUsuarioDAO;
import com.example.demo1.model.Usuario;






@Service
public class UserService implements UserDetailsService {

	@Autowired
	private IUsuarioDAO udao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario us = udao.findByUsername(username);
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for (int i = 0; i < us.getUser_rol().size(); i++) {
			roles.add(new SimpleGrantedAuthority(us.getUser_rol().get(i).getRole_name()));
		}
		
		UserDetails userdet = new User(us.getUsername(),us.getPassword(),roles);
		return userdet;
	}

	

}
