package com.oxahex.product.config;

import com.oxahex.product.dto.ResponseDto;
import com.oxahex.product.exception.ProductException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseDto.Fail handleUserException(ProductException e) {
        // TODO 로거 사용

        return ResponseDto.Fail.builder()
                .errorCode(e.getResponseCode().value())
                .errorMessage(e.getResponseMessage()).build();
    }

}
