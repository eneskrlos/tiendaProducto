package com.example.demo1.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.demo1.model.Categoria;
import com.example.demo1.model.Imagen;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoCreateResponse {

	@JsonProperty("sku")
	private int sku;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("precio")
	private double precio;
	@JsonProperty("cantStock")
	private int cantStock;
	@JsonProperty("categoriaId")
	private Categoria categoriaId;
	@JsonProperty("tags")
	private String tags;
	@JsonProperty("descripcion")
	private String descripcion;
	@JsonProperty("infoAdiconal")
	private String infoAdiconal;
	@JsonProperty("valoracion")
	private int valoracion;
	@JsonProperty("imagen")
	private List<Imagen> imagen;

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
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
	public int getCantStock() {
		return cantStock;
	}
	public void setCantStock(int cantStock) {
		this.cantStock = cantStock;
	}
	public Categoria getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Categoria categoriaId) {
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
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public List<Imagen> getImagen() {
		return imagen;
	}
	public void setImagen(List<Imagen> imagen) {
		this.imagen = imagen;
	}
	
}
