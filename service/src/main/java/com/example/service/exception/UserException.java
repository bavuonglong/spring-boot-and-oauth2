package com.example.service.exception;

import org.springframework.http.HttpStatus;

public class UserException extends GeneralException {

    public UserException(HttpStatus httpStatus, String code, String message) {
        super(httpStatus, code, message);
    }
}
