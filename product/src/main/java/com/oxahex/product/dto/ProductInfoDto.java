package com.oxahex.product.dto;

import com.oxahex.domain.type.OrganizationCode;
import com.oxahex.domain.type.ProductCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


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
}
