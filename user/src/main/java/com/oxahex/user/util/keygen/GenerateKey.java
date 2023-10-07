package com.oxahex.user.util.keygen;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class GenerateKey {
    public String getKey() {
        return UUID.randomUUID().toString()
                .replace("-", "");
    }
}
