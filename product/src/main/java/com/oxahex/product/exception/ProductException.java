package com.oxahex.product.exception;

import com.oxahex.product.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductException extends RuntimeException {
    private HttpStatus responseCode;
    private String responseMessage;

    public ProductException(ErrorCode errorCode) {
        this.responseCode = errorCode.getStatus();
        this.responseMessage = errorCode.getDescription();
    }
}
