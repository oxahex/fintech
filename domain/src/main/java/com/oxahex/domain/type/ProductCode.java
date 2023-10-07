package com.oxahex.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProductCode {

    PRODUCT_ONE("001"),
    PRODUCT_TWO("002"),
    PRODUCT_THREE("003");

    private final String productCode;

    public static ProductCode getCode(String data) {
        System.out.println("productCode data: " + data);
        return Arrays.stream(ProductCode.values())
                .filter(v -> v.getProductCode().equals(data))
                .findAny().orElseThrow(() -> new NullPointerException(
                        String.format("ProductCode에 %s가 존재하지 않습니다.", data)
                ));
    }
}
