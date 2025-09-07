package com.github.regyl.service.createaccount.supplier;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @see <a href="https://popname.ru/rating-100-names">first names</a>
 */
@Component
public class FirstNameSupplier implements Supplier<String> {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final List<String> NAMES;

    static {
        NAMES = new ArrayList<>();
        NAMES.add("Александр");
        NAMES.add("Сергей");
        NAMES.add("Дмитрий");
        NAMES.add("Елена");
        NAMES.add("Андрей");
        NAMES.add("Анастасия");
        NAMES.add("Анна");
        NAMES.add("Екатерина");
        NAMES.add("Алексей");
        NAMES.add("Максим");
        NAMES.add("Иван");
        NAMES.add("Наталья");
        NAMES.add("Наталия");
        NAMES.add("Элена");
        NAMES.add("Владимир");
        NAMES.add("Евгений");
        NAMES.add("Татьяна");
        NAMES.add("Виктория");
        NAMES.add("Ольга");
        NAMES.add("Михаил");
        NAMES.add("Ирина");
        NAMES.add("Артем");
        NAMES.add("Николай");
        NAMES.add("Юлия");
        NAMES.add("Владислав");
        NAMES.add("Даниил");
        NAMES.add("Данил");
        NAMES.add("Роман");
        NAMES.add("Денис");
        NAMES.add("Игорь");
        NAMES.add("Дима");
        NAMES.add("Никита");
        NAMES.add("Антон");
        NAMES.add("Олег");
        NAMES.add("Павел");
        NAMES.add("Юрий");
        NAMES.add("Василий");
        NAMES.add("Виктор");
        NAMES.add("Кирилл");
        NAMES.add("Кирил");
    }

    @Override
    public String get() {
        int index = SECURE_RANDOM.nextInt(0, NAMES.size() - 1);
        return NAMES.get(index);
    }
}