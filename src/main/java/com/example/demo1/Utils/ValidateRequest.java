package com.example.demo1.Utils;

import com.example.demo1.Utils.enums.ErrorCode;
import com.example.demo1.request.RequestCreateProducto;


public class ValidateRequest {
	
	private ValidateRequest() {
    }

	public static void validateProducto(RequestCreateProducto request) throws TiendaExceptions {
		
		if(request.getNombre() == null  || request.getCategoriaId() == null || request.getDescripcion() == null) {
			throw new TiendaExceptions(ErrorCode.NULL_ELEMENT, "Existen algunos campos vacios");
		}
		
		if(request.getPrecio() == 0 ) {
			throw new TiendaExceptions(ErrorCode.INVALID_PRECIO, "EL precio debe ser mayor que cero");
		}
		
		if(request.getImagen().size() == 0 || request.getImagen() == null) {
			throw new TiendaExceptions(ErrorCode.NOT_CONTENT_IMAGEN, "El producto debe tener al menos una imagen");
		}
		
	 }
}



