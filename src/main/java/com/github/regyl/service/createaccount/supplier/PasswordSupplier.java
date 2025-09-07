package com.github.regyl.service.createaccount.supplier;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Component
public class PasswordSupplier implements Supplier<String> {

    @Override
    public String get() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!";
        int index = ThreadLocalRandom.current().nextInt(8, 15);
        return RandomStringUtils.random( index, characters );
    }
}
