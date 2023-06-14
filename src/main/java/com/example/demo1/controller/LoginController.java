package com.example.demo1.controller;

import java.time.Instant;

import static java.util.stream.Collectors.joining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.model.Usuario;
import com.example.demo1.request.AuthRequest;

import com.example.demo1.response.AuthResponse;

import com.example.demo1.response.UserResponse;
import com.example.demo1.service.UsuarioService;


@RequestMapping(path = "api")
@RestController
public class LoginController {
	
	@Autowired
	private  AuthenticationManager authManager;
	@Autowired
	private  JwtEncoder jwtEncoder;
	@Autowired
	private UsuarioService userService;
	
	@PostMapping("login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
		System.out.println(request);
		
		var autentication = 
				authManager
				.authenticate(
						new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		var user = (UserDetails)autentication.getPrincipal();
		System.out.println(user);
		
		var now = Instant.now();
	    var expiry = 36000L;
	      
	    var scope =
	    		autentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
	              .collect(joining(" "));
	                
	    var claims =
	            JwtClaimsSet.builder()
	                .issuer("example.tienda")
	                .issuedAt(now)
	                .expiresAt(now.plusSeconds(expiry))
	                .subject(String.format("%s", user.getUsername()))
	                .claim("roles", scope)
	                .build();
	    
	    var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	    
	    return ResponseEntity.ok()
	            .header(HttpHeaders.AUTHORIZATION, token)
	            .body(mapUserAuth(user.getUsername(), token));
		
		
	}
	
	
	@PostMapping("register")
	  public ResponseEntity<UserResponse> register(@RequestBody Usuario request) {
	    return userService.addUsuario(request);
	  }
	
	private AuthResponse mapUserAuth(String user, String token) {
		AuthResponse authresp = new AuthResponse();
		authresp.setUsername(user);
		authresp.setToken(token);
		return authresp;
	}
	
	
}
