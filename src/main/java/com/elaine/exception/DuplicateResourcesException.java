package com.elaine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateResourcesException extends RuntimeException{
    public DuplicateResourcesException(String message) {
        super(message);
    }
}
