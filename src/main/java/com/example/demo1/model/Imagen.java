package com.example.demo1.model;



import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="imagen")
public class Imagen {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NonNull
	@Column(name="url_img")
	private String urlImg;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prod_id")
	private Producto prodid;
	
	public Imagen() {
		
	}

	public Imagen(Integer id, String urlImg, Producto prodid) {
		super();
		this.id = id;
		this.urlImg = urlImg;
		this.prodid = prodid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public Producto getProdid() {
		return prodid;
	}

	public void setProdid(Producto prodid) {
		this.prodid = prodid;
	}
	
	
	
	
	
}
