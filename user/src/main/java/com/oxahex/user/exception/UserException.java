package com.oxahex.user.exception;

import com.oxahex.user.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserException extends RuntimeException {
    private HttpStatus responseCode;
    private String responseMessage;

    public UserException(ErrorCode errorCode) {
        this.responseCode = errorCode.getStatus();
        this.responseMessage = errorCode.getDescription();
    }
}
