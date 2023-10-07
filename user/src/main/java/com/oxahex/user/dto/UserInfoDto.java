package com.oxahex.user.dto;

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
        private String userRegistrationNumber;
    }

    @Getter
    @Builder
    @ToString
    public static class Response {
        private String userKey;
        private String userRegistrationNumber;
    }
}
