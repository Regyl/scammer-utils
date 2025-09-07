package com.github.regyl.service.createaccount.supplier;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Component
public class PhoneSupplier implements Supplier<String> {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final List<String> PREFIXES;

    static {
        PREFIXES = new ArrayList<>();
        PREFIXES.add("495");
        PREFIXES.add("967");
        PREFIXES.add("926");
        PREFIXES.add("790");
        PREFIXES.add("123");
        PREFIXES.add("903");
        PREFIXES.add("900");
        PREFIXES.add("800");
        PREFIXES.add("945");
        PREFIXES.add("496");
        PREFIXES.add("497");
        PREFIXES.add("623");
        PREFIXES.add("523");
        PREFIXES.add("807");
        PREFIXES.add("903");
        PREFIXES.add("125");
    }

    @Override
    public String get() {
        StringBuilder sb = new StringBuilder("7");

        int index = SECURE_RANDOM.nextInt(0, PREFIXES.size() - 1);
        sb.append(PREFIXES.get(index));

        for (int i = 0; i < 7; i++) {
            int next = ThreadLocalRandom.current().nextInt(0, 9);
            sb.append(next);
        }
        return sb.toString();
    }
}
