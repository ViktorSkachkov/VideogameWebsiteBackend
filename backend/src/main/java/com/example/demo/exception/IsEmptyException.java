package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IsEmptyException extends ResponseStatusException {
    public IsEmptyException() {
        super(HttpStatus.BAD_REQUEST, "Is Empty");
    }
}
