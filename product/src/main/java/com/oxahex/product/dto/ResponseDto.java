package com.oxahex.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Success {
        private final Object data;
        private final int responseCode;
        private final String responseMessage;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Fail {
        private final int errorCode;
        private final String errorMessage;
    }
}
