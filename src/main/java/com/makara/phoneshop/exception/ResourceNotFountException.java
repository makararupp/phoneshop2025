package com.makara.phoneshop.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFountException extends ApiException{
    public ResourceNotFountException(String resourceName, Long id) {
        super(HttpStatus.NOT_FOUND,
                String.format("%s with id =%d not found",resourceName,id));
    }
}
