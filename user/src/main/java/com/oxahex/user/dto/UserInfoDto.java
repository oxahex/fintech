package com.oxahex.user.dto;

import com.oxahex.user.annotation.Encrypt;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserInfoDto {

    @Getter
    @ToString
    public static class Request {
        private Long userIncomeAmount;
        private String userName;

        @Encrypt
        private String userRegistrationNumber;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class Response {
        private String userKey;

        @Encrypt
        private String userRegistrationNumber;
    }
}
