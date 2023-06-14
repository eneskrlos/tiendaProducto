package com.example.demo1.Utils.enums;

public enum ErrorCode {

	BAD_REQUEST("01", "BAD_REQUEST"),
	NULL_ELEMENT("02","NULL_ELEMENT"),
	INVALID_PRECIO("03","INVALID_PRECIO"),
	NOT_CONTENT_IMAGEN("04","NOT_CONTENT_IMAGEN"),
	INTERNAL_ERROR("99", "INTERNAL ERROR");
	
	private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
