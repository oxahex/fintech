package com.oxahex.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 유저입니다."),
    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 유저입니다."),
    ENCRYPT_FAIL(HttpStatus.BAD_REQUEST, "올바른 값이 아닙니다.");

    private final HttpStatus status;
    private final String description;
}
