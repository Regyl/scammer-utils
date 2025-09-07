package com.github.regyl.service.resetpassword;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Component
public class ExistEmailSupplier implements Supplier<String> {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final List<String> EMAILS;

    static {
        EMAILS = new ArrayList<>();

        EMAILS.add("fuckyou@gmail.com");
        EMAILS.add("virgil.crist@hotmail.com");
        EMAILS.add("darrell.purdy@gmail.com");
        EMAILS.add("myles.kunde@hotmail.com");
        EMAILS.add("elia.quitzon@hotmail.com");
        EMAILS.add("emmitt.romaguera@gmail.com");
        EMAILS.add("darrell.purdy@gmail.com");
        EMAILS.add("myles.kunde@hotmail.com");

        EMAILS.add("virgil.crist@hotmail.com");
        EMAILS.add("dmitriyevyuliya7@yandex.ru");
        EMAILS.add("yefimovroman@yandex.ru");
        EMAILS.add("myles.kunde@hotmail.com");
        EMAILS.add("osipovdanil0@mail.ru");
        EMAILS.add("dmitriyev.yelena@yandex.ru");
        EMAILS.add("yelena-sokolov@mail.ru");
    }

    @Override
    public String get() {
        int index = SECURE_RANDOM.nextInt(0, EMAILS.size() - 1);
        return EMAILS.get(index);
    }
}
