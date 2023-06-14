package com.example.demo1.Utils;

import com.example.demo1.Utils.enums.ErrorCode;

@SuppressWarnings("serial")
public class TiendaExceptions extends Exception {

	private final ErrorCode code;
    private final String message;
    
    
	public TiendaExceptions(ErrorCode code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ErrorCode getCode() {
		return code;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
    
	public String getExceptionCode() {
	   return code.getCode();
	}

	public String getExceptionMessage() {
	   return code.getMessage() + ", " + message;
	}

    
    
    
    
}
