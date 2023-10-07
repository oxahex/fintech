package com.oxahex.product.dto;

import com.oxahex.domain.type.OrganizationCode;
import com.oxahex.domain.type.ProductCode;
import lombok.*;


public class ProductInfoDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Request {
        private String organizationCode;
        private String productCode;
        private Double productMaximumInterest;
        private Double productMinimumInterest;
        private String productName;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Response {
        private OrganizationCode organizationCode;
        private ProductCode productCode;
        private Double productMaximumInterest;
        private Double productMinimumInterest;
        private String productName;
    }
}
