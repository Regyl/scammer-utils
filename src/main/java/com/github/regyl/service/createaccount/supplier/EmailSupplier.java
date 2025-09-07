package com.github.regyl.service.createaccount.supplier;

import com.ibm.icu.text.Transliterator;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class EmailSupplier implements Function<Pair<String, String>, String> {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final Transliterator toLatin = Transliterator.getInstance("Russian-Latin/BGN");

    private static final List<String> POSTFIXES;

    static {
        POSTFIXES = new ArrayList<>();

        POSTFIXES.add("@gmail.com");
        POSTFIXES.add("@yandex.ru");
        POSTFIXES.add("@mail.ru");
        POSTFIXES.add("@hotmail.ru");
        POSTFIXES.add("@yahoo.com");
    }

    @Override
    public String apply(Pair<String, String> stringStringPair) {
        StringBuilder sb = new StringBuilder();
        String firstName = stringStringPair.getLeft();
        String lastName = stringStringPair.getRight();

        String fixedFirstName = toLatin.transform(firstName).toLowerCase();
        String fixedLastName = toLatin.transform(lastName).toLowerCase();
        int random = SECURE_RANDOM.nextInt();
        if (random % 2 == 0) {
            sb.append(fixedFirstName);
            if (random % 2 == 0) {
                sb.append("-");
            } else if (random % 3 == 0) {
                sb.append(".");
            } else {
                //do nothing
            }
            sb.append(fixedLastName);
        } else {
            sb.append(fixedLastName);
            if (random % 2 == 0) {
                sb.append("-");
            } else if (random % 3 == 0) {
                sb.append(".");
            } else {
                //do nothing
            }
            sb.append(fixedFirstName);
        }

        for (int i = 0; i < 6; i++) {
            random = SECURE_RANDOM.nextInt();
            if (random % 2 == 0) {
                sb.append(SECURE_RANDOM.nextInt(0, 9));
            }
        }

        int index = SECURE_RANDOM.nextInt(0, POSTFIXES.size() - 1);
        String postfix = POSTFIXES.get(index);
        return sb.append(postfix).toString().replaceAll("ʹ", "");
    }

    private boolean is() {
        return SECURE_RANDOM.nextInt() % 2 == 0;
    }
}
