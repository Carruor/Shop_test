package com.adobe.bookstore.Exceptions;

public class OrderException extends RuntimeException{

    private String code;

    public OrderException(String message, String code) {
        super(message);
        this.code= code;
    }

    public String getCode() {
        return code;
    }

}
