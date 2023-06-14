package com.example.demo1.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoResponse {
	
	@JsonProperty("sku")
	private int sku;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("precio")
	private double precio;
	@JsonProperty("cantStock")
	private int cantStock;
	@JsonProperty("categoria")
	private String categoria;
	@JsonProperty("valoracion")
	private int valoracion;
	@JsonProperty("imagenes")
	private List<ImagenResponse> imagenes;


	public ProductoResponse() {

	}

	public List<ImagenResponse> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<ImagenResponse> imagenes) {
		this.imagenes = imagenes;
	}

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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	
	
}
