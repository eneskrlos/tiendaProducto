package com.example.demo1.model;

import java.util.List;




import org.springframework.lang.NonNull;

import com.example.demo1.model.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NonNull
	@Column(name="nombre")
	private String nombre;
	@Column(name="producto")
	@OneToMany(mappedBy = "categoriaId", fetch = FetchType.LAZY)
	private List<Producto> prod;
	
	
	public Categoria() {
		
	}



	public Categoria(int id, String nombre, List<Producto> prod) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.prod = prod;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Producto> getProdId() {
		return prod;
	}



	public void setProdId(List<Producto> prod) {
		this.prod = prod;
	}
	
	
	
	
}
