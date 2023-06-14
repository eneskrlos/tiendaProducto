package com.example.demo1.utilencrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncryptService implements IEncryptService {

	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bcryptpass = new BCryptPasswordEncoder();
		return bcryptpass;
	}
	
	@Override
	public String encryptarPassword(String password) {
		return passwordEncoder().encode(password);
	}

	

}
