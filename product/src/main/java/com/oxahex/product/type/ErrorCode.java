package com.oxahex.product.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_ORGANIZATION_CODE(HttpStatus.NOT_FOUND,"존재하지 않는 기관 코드입니다.");

    private final HttpStatus status;
    private final String description;
}
