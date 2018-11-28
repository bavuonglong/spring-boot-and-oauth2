package com.example.service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class GeneralException extends RuntimeException {
    private HttpStatus status;
    private String code;
    private String message;

    public GeneralException(HttpStatus httpStatus, String code, String message) {
        this.status = httpStatus;
        this.code = code;
        this.message = message;
    }
}
