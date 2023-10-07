package com.oxahex.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrganizationCode {

    ORGANIZATION_ONE("00001"),
    ORGANIZATION_TWO("00002"),
    ORGANIZATION_THREE("00003");

    private final String organizationCode;

    public static OrganizationCode getCode(String data) {
        System.out.println("Organization data: " + data);

        return Arrays.stream(OrganizationCode.values())
                .filter(v -> v.getOrganizationCode().equals(data))
                .findAny().orElseThrow(() -> new NullPointerException(
                        String.format("Organization Code에 %s가 존재하지 않습니다.", data)
                ));
    }
}
