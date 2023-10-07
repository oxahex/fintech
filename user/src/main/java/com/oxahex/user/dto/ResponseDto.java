package com.oxahex.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

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
        private final int status;
        private final String errorMessage;
    }
}
