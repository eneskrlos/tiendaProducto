package com.example.demo1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Producto;

@Repository
public interface IProductoDAO extends JpaRepository<Producto,Integer>{

	@Query("select p.sku , p.nombre , p.precio ,"
			+ "p.cantStock , p.valoracion , c.nombre as categoria "
			+ "from Producto p inner join Categoria c on p.categoriaId = c.id ")
	List<Object[]> listProductosConCategoria();

	@Query("select p.sku , p.nombre , p.precio ,"
			+ "p.cantStock , p.valoracion , c.nombre as categoria "
			+ "from Producto p inner join Categoria c on p.categoriaId = c.id "
			+ "where p.sku = :sku")
	Object[] getProducto(Integer sku);
	
	
}
