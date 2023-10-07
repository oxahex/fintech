package com.oxahex.user.config;

import com.oxahex.user.dto.ResponseDto;
import com.oxahex.user.exception.SecurityException;
import com.oxahex.user.exception.UserException;
import com.oxahex.user.type.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.GeneralSecurityException;
import java.security.Security;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseDto.Fail handleUserException(UserException e) {
        // TODO 로거 사용

        return ResponseDto.Fail.builder()
                .errorCode(e.getResponseCode().value())
                .errorMessage(e.getResponseMessage()).build();
    }

    @ExceptionHandler(GeneralSecurityException.class)
    public ResponseDto.Fail handleGeneralSecurityException(GeneralSecurityException e) {
        return ResponseDto.Fail.builder()
                .errorCode(ErrorCode.ENCRYPT_FAIL.getStatus().value())
                .errorMessage(ErrorCode.ENCRYPT_FAIL.getDescription()).build();
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
//
//        System.out.println("RuntimeException: " + e.getClass().getSimpleName());
//        if (e.getClass().isInstance(GeneralSecurityException.class)) {
//
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
