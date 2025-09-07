package com.github.regyl.service.createaccount.supplier;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class KeyGenerator implements Supplier<String> {

    @Override
    public String get() {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        return RandomStringUtils.random( 64, characters );
    }
}
