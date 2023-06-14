package com.example.demo1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo1.Utils.TiendaExceptions;
import com.example.demo1.Utils.ValidateRequest;
import com.example.demo1.dao.IImagenDAO;
import com.example.demo1.dao.IProductoDAO;
import com.example.demo1.model.Imagen;
import com.example.demo1.model.Producto;
import com.example.demo1.request.RequestCreateProducto;
import com.example.demo1.response.ImagenResponse;
import com.example.demo1.response.ProductoResponse;

@Service
public class ProductoService {

	@Autowired
	private IProductoDAO productodao;
	
	@Autowired
	private IImagenDAO imgDao;
	
	
	public ResponseEntity<List<ProductoResponse>> getlistProducto(){
		try {
			
			var productos = productodao.listProductosConCategoria();
			List<ProductoResponse> response = new ArrayList<>();
			for (int i = 0; i < productos.size(); i++) {
				ProductoResponse pr = new ProductoResponse () ;
				response.add(mappearDatos(productos.get(i), pr));
			}
			
			return ResponseEntity.ok(response);
		} catch(Exception e) {
			System.out.print(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<ProductoResponse> getProductById(Integer sku){
		try {
			Optional<Producto> pro = productodao.findById(sku);
			if(pro.isPresent()) {
				ProductoResponse result = mapProductoToProductoResponse(pro.get(), new ProductoResponse());
				return ResponseEntity.ok(result);
			}
			else {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
	
	public ResponseEntity<ProductoResponse> createProduct(RequestCreateProducto request) throws TiendaExceptions {
		System.out.print("llego");
		ValidateRequest.validateProducto(request);
		
		List<Imagen> auxImagen = listImagen(request.getImagen());
		
		Producto auxProd = mapRequestToProducto(request); 
		Producto productoguardado = productodao.save(auxProd);
		if(productoguardado == null) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			guardarImagenes(auxImagen,productoguardado);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(mapProductoToProductoResponse(productoguardado, new ProductoResponse()));
	}
	
	private void guardarImagenes(List<Imagen> auxImagen, Producto productoguardado) throws Exception {
		try {

			auxImagen.forEach(img -> {
				img.setProdid(productoguardado);
				imgDao.save(img);
			});
		} catch (Exception e) {
			throw new Exception();
		}
	}

	private List<Imagen> listImagen(List <String> urls) {
		List<String> auxUrlImagen = urls;
		List<Imagen> listImages = new ArrayList<>();
		auxUrlImagen.forEach(url -> {
			Imagen auxImg = new Imagen();
			auxImg.setId(0);
			auxImg.setUrlImg(url);
			listImages.add(auxImg);
		});
		return listImages;
	}
	
	
	
	public ResponseEntity<String> deleteProductById(Integer id) {
		Optional<Producto> pro = productodao.findById(id);
		if(pro.isPresent()) {
			List<Imagen> imagenes = mapImagenesDaoToImagen(pro.get().getImagenes());
			imgDao.deleteAll(imagenes);
			productodao.deleteById(id);
			return ResponseEntity.ok("Se elemino correctamente");
		}
		else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	

	//Mapping methods
	private ProductoResponse mappearDatos(Object[] objects, ProductoResponse pr) {
		pr.setSku((Integer)objects[0]);
		pr.setNombre(objects[1].toString());
		pr.setPrecio((Double)objects[2]);
		pr.setCantStock((Integer)objects[3]);
		pr.setValoracion((Integer)objects[4]);
		pr.setCategoria(objects[5].toString());
		return pr;
	}
	
	private ProductoResponse mapProductoToProductoResponse(Producto objects, ProductoResponse pr) {
		pr.setSku(objects.getSku());
		pr.setNombre(objects.getNombre());
		pr.setPrecio(objects.getPrecio());
		pr.setCantStock(objects.getCantStock());
		pr.setValoracion(objects.getValoracion());
		pr.setCategoria(objects.getCategoria().getNombre());
		List<Imagen> aux = (List<Imagen>)objects.getImagenes();
		List<ImagenResponse> mapearImagen = new ArrayList<>();
		aux.forEach(imag -> {
			ImagenResponse imageResponse = new ImagenResponse();
			imageResponse.setId(imag.getId());
			imageResponse.setUrlImg(imag.getUrlImg());
			mapearImagen.add(imageResponse);
		});
		pr.setImagenes(mapearImagen);
		return pr;
	}
	
	private Producto mapRequestToProducto(RequestCreateProducto request) {
		Producto prod = new Producto();
		prod.setCantStock(request.getCantStock());
		prod.setCategoria(request.getCategoriaId());
		prod.setNombre(request.getNombre());
		prod.setDescripcion(request.getDescripcion());
		prod.setInfoAdiconal(request.getInfoAdiconal());
		prod.setPrecio(request.getPrecio());
		prod.setTags(request.getTags());
		prod.setValoracion(request.getValoracion());
		return prod;
	}
	
	private List<Imagen> mapImagenesDaoToImagen(List<Imagen> imagen) {
		List<Imagen> map = new ArrayList<Imagen>();
		imagen.forEach( img -> {
			Imagen i = new Imagen();
			i.setId(img.getId());
			i.setUrlImg(img.getUrlImg());
			i.setProdid(img.getProdid());
			map.add(img);
		});
		return map;
	}
	
}
