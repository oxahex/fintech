package com.oxahex.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BaseResponseDto {
    private final int responseCode;
    private final String responseMessage;
}
