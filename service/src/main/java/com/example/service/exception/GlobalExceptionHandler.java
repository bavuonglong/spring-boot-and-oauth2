package com.example.service.exception;

import com.example.service.dto.response.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ServiceResponse> handleUserException(UserException userException) {
        return ResponseEntity.status(userException.getStatus()).body(
                ServiceResponse.builder()
                        .code(userException.getCode())
                        .message(userException.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(ClientAuthenticationException.class)
    public ResponseEntity<ServiceResponse> handleOAuth2Exception(ClientAuthenticationException exception) {
        return ResponseEntity.status(exception.getHttpErrorCode()).body(
                ServiceResponse.builder()
                        .code("400")
                        .message("Oauth2 exception")
                        .data(exception.getAdditionalInformation())
                        .build()
        );
    }
}
