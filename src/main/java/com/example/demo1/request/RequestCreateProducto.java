package com.example.demo1.request;




import java.util.List;

import com.example.demo1.model.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCreateProducto {

	@JsonProperty("sku")
	private int sku;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("precio")
	private double precio;
	@JsonProperty("cantStock")
	private Integer cantStock;
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
	private List<String> imagen;

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
	public Integer getCantStock() {
		return cantStock;
	}
	public void setCantStock(Integer cantStock) {
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
	public List<String> getImagen() {
		return imagen;
	}
	public void setImagen(List<String> imagen) {
		this.imagen = imagen;
	}
	
	
}
