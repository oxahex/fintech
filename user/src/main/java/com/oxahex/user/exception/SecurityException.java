package com.oxahex.user.exception;

import com.oxahex.user.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.security.GeneralSecurityException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityException extends GeneralSecurityException {
    private HttpStatus responseCode;
    private String responseMessage;

    public SecurityException(ErrorCode errorCode) {
        this.responseCode = errorCode.getStatus();
        this.responseMessage = errorCode.getDescription();
    }
}
