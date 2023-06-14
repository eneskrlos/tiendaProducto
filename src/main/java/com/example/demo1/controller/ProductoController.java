package com.example.demo1.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.Utils.TiendaExceptions;
import com.example.demo1.request.RequestCreateProducto;
import com.example.demo1.response.ProductoResponse;
import com.example.demo1.service.ProductoService;


@RestController
@RequestMapping("productos")
public class ProductoController {

	@Autowired
	private ProductoService prodservice;
	
	@GetMapping(value = "hello")
	public String Hello () {
		return "Hello Word";
	}
	
	@GetMapping
	public ResponseEntity<List<ProductoResponse>> listProduct(){
		return prodservice.getlistProducto();
	}
	
	
	
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<ProductoResponse> getProduct(@PathVariable("id") Integer id) {
		return prodservice.getProductById(id);
	}
	
	
	
	@PostMapping(value="/crear")
	public ResponseEntity<ProductoResponse> createProduct(@RequestBody RequestCreateProducto request) throws TiendaExceptions {
		System.out.println("createProducto");
		return prodservice.createProduct(request);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
		return prodservice.deleteProductById(id);
	}
	
	
}
