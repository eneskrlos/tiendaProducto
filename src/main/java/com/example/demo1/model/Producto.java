package com.example.demo1.model;

//import java.util.ArrayList;
import java.util.List;


import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sku;
	@NonNull
	@Column(name="nombre", length = 225)
	private String nombre;
	@NonNull
	@Column(name="precio")
	private double precio;
	@Column(name="cant_stock")
	private Integer cantStock;
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoriaId;
	@Column(name="tags")
	private String tags;
	@Column(name="descripcion", length = 225)
	private String descripcion;
	@Column(name="info_adiconal", length = 225)
	private String infoAdiconal;
	@Column(name="valoracion")
	private Integer valoracion;
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Imagen> imagenes ;
	
	public Producto() {
		
	}
	
	

	public Producto(Integer sku, String nombre, double precio, Integer cantStock, Categoria categoriaId, String tags,
			String descripcion, String infoAdiconal, Integer valoracion, List<Imagen> imagenes) {
		super();
		this.sku = sku;
		this.nombre = nombre;
		this.precio = precio;
		this.cantStock = cantStock;
		this.categoriaId = categoriaId;
		this.tags = tags;
		this.descripcion = descripcion;
		this.infoAdiconal = infoAdiconal;
		this.valoracion = valoracion;
		this.imagenes = imagenes;
	}



	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Integer getCantStock() {
		return cantStock;
	}

	public void setCantStock(Integer cantStock) {
		this.cantStock = cantStock;
	}

	public Categoria getCategoria() {
		return categoriaId;
	}

	public void setCategoria(Categoria categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getInfoAdiconal() {
		return infoAdiconal;
	}

	public void setInfoAdiconal(String infoAdiconal) {
		this.infoAdiconal = infoAdiconal;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
	
	
}
