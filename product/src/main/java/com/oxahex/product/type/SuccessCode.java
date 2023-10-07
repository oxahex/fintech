package com.oxahex.product.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode{
    SUCCESS(HttpStatus.OK,"요청이 성공적으로 완료되었습니다.");


    private final HttpStatus status;
    private final String description;
}
