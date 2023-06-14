package com.example.demo1.config;



import java.io.File;
import java.io.StringReader;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.print.DocFlavor.BYTE_ARRAY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.demo1.Utils.ClassKeyPrimary;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;




@EnableWebSecurity
@Configuration(enforceUniqueMethods = false)
public class SecurityConfig   {

	
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Value("${spring.security.oauth2.resourceserver.jwt.public-key-location}")
	private RSAPublicKey rsaPublicKey;
	
	
	//private RSAPrivateKey rsaPrivateKey = ClassKeyPrimary.getRsaPrivate();
	
	// Cargar la clave privada desde un archivo
	
	
	
	/*
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bcryptpass = new BCryptPasswordEncoder();
		return bcryptpass;
	}
	*/
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bcryptpass) throws Exception {
		return authenticationManagerConfig(http, bcryptpass).and().build();
	}
	
	
	public DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> authenticationManagerConfig(HttpSecurity http, BCryptPasswordEncoder _bcryptpass) throws Exception {
		
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailService).passwordEncoder(_bcryptpass);
		
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//Desabilito los cors
		http.cors().and().csrf().disable();
		//Seteo el manejador desessiones
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Seteo el controlador de excepciones de solicitudes no autorizadas
		http.exceptionHandling(
				(exceptions) -> 
					exceptions.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
					.accessDeniedHandler(new BearerTokenAccessDeniedHandler()));
		//Configuro permisos en los endpoints
		http.authorizeRequests()
		.requestMatchers(HttpMethod.GET, "Hello").permitAll()
		.requestMatchers(HttpMethod.GET, "/usuarios").permitAll()
		.requestMatchers(HttpMethod.GET, "/usuarios/{id}").permitAll().anyRequest()
		.authenticated()
		.and()
		.httpBasic(Customizer.withDefaults())
		.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        
		return http.build();
	}
	
	
	 // Uso el JwtAuthenticationProvider para generar el JWT tokens
	  @Bean
	  JwtEncoder jwtEncoder() {
	    
		var jwk = new RSAKey.Builder(this.rsaPublicKey).build();
	    var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
	    return new NimbusJwtEncoder(jwks);
	  }
	  
	  // Usado  para JwtAuthenticationProvider para decodificar y validar el JWT tokens
	  @Bean
	  JwtDecoder jwtDecoder() {
	    return NimbusJwtDecoder.withPublicKey(this.rsaPublicKey).build();
	  }
	  
	// Extraer autoridades de la notificación de roles
	  @Bean
	  JwtAuthenticationConverter jwtAuthenticationConverter() {
	    var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
	    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

	    var jwtAuthenticationConverter = new JwtAuthenticationConverter();
	    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
	    return jwtAuthenticationConverter;
	  }

	  @Bean
	  PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }


	  // Usado por spring security si los  CORS son abilitados.
	  @Bean
	  CorsFilter corsFilter() {
	    var source = new UrlBasedCorsConfigurationSource();
	    var config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	  }

	  // Exponer el bean del administrador de autenticación
	  @Bean
	  AuthenticationManager authenticationManager(
	      AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	  }


	
	
}
