package com.example.demo1.utilencrypt;

import org.springframework.stereotype.Service;

@Service
public interface IEncryptService {
	
	String encryptarPassword(String password);
	
	//boolean verificarPassword(String originalPass, String hasPassword);
}
