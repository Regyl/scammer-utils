package com.github.regyl.service.createaccount.supplier;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @see <a href="https://metrics.tilda.ws/surnames">surnames</a>
 */
@Component
public class LastNameSupplier implements Supplier<String> {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final List<String> NAMES;

    static {
        NAMES = new ArrayList<>();
        NAMES.add("Иванов");
        NAMES.add("Васильев");
        NAMES.add("Петров");
        NAMES.add("Смирнов");
        NAMES.add("Михайлов");
        NAMES.add("Федоров");
        NAMES.add("Соколов");
        NAMES.add("Яковлев");
        NAMES.add("Попов");
        NAMES.add("Андреев");
        NAMES.add("Алексеев");
        NAMES.add("Александров");
        NAMES.add("Лебедев");
        NAMES.add("Григорьев");
        NAMES.add("Степанов");
        NAMES.add("Семенов");
        NAMES.add("Павлов");
        NAMES.add("Богданов");
        NAMES.add("Николаев");
        NAMES.add("Дмитриев");
        NAMES.add("Егоров");
        NAMES.add("Волков");
        NAMES.add("Кузнецов");
        NAMES.add("Никитин");
        NAMES.add("Соловьев");
        NAMES.add("Тимофеев");
        NAMES.add("Орлов");
        NAMES.add("Афанасьев");
        NAMES.add("Филиппов");
        NAMES.add("Сергеев");
        NAMES.add("Захаров");
        NAMES.add("Матвеев");
        NAMES.add("Виноградов");
        NAMES.add("Кузьмин");
        NAMES.add("Максимов");
        NAMES.add("Козлов");
        NAMES.add("Ильин");
        NAMES.add("Герасимов");
        NAMES.add("Марков");
        NAMES.add("Новиков");
        NAMES.add("Морозов");
        NAMES.add("Романов");
        NAMES.add("Осипов");
        NAMES.add("Макаров");
        NAMES.add("Зайцев");
        NAMES.add("Беляев");
        NAMES.add("Гаврилов");
        NAMES.add("Антонов");
        NAMES.add("Ефимов");
        NAMES.add("Леонтьев");
        NAMES.add("Давыдов");
        NAMES.add("Гусев");
        NAMES.add("Данилов");
        NAMES.add("Киселев");
        NAMES.add("Сорокин");
        NAMES.add("Тихомиров");
        NAMES.add("Крылов");
        NAMES.add("Никифоров");
        NAMES.add("Кондратьев");
        NAMES.add("Кудрявцев");
        NAMES.add("Борисов");
        NAMES.add("Жуков");
        NAMES.add("Воробьев");
        NAMES.add("Щербаков");
        NAMES.add("Поляков");
        NAMES.add("Савельев");
        NAMES.add("Шмидт");
        NAMES.add("Трофимов");
        NAMES.add("Чистяков");
        NAMES.add("Баранов");
        NAMES.add("Сидоров");
        NAMES.add("Соболев");
        NAMES.add("Карпов");
        NAMES.add("Белов");
        NAMES.add("Миллер");
        NAMES.add("Титов");
        NAMES.add("Львов");
        NAMES.add("Фролов");
        NAMES.add("Игнатьев");
        NAMES.add("Комаров");

        NAMES.add("Прокофьев");
        NAMES.add("Быков");
        NAMES.add("Абрамов");
        NAMES.add("Голубев");
    }

    @Override
    public String get() {
        int index = SECURE_RANDOM.nextInt(0, NAMES.size() - 1);
        return NAMES.get(index);
    }
}