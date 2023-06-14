package com.example.demo1.Utils;

import java.io.File;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public  class ClassKeyPrimary {

	public static final String RSA_PRIVATE = "-----BEGIN PRIVATE KEY-----\n"
			+ "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\n"
			+ "XVEH0VishgwbYXOJCSBlZD7h0yS2\n"
			+ "-----END PRIVATE KEY-----";

	public static String getRsaPrivate() {
		return RSA_PRIVATE;
	}
	
	public static RSAPrivateKey getPrymarykey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Leer el archivo de clave privada
		File keyFile = new File("src/main/resources/rsa.private.key");
		byte[] keyBytes = ClassKeyPrimary.RSA_PRIVATE.getBytes();

		// Crear una especificación de clave privada PKCS#8
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

		// Crear una instancia de la fábrica de claves RSA
		KeyFactory factory = KeyFactory.getInstance("RSA");

		// Generar la clave privada a partir de la especificación
		PrivateKey privateKey = factory.generatePrivate(spec);
		RSAPrivateKey aux = RSAPrivateKey.class.cast(privateKey);
		return aux;
	}
	
}
