package com.github.regyl.service.createaccount;

import com.github.regyl.service.createaccount.supplier.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class PayloadBuilderServiceImpl implements Supplier<Map<String, Object>> {

    private final FirstNameSupplier firstNameSupplier;
    private final LastNameSupplier lastNameSupplier;
    private final EmailSupplier emailSupplier;
    private final PhoneSupplier phoneSupplier;
    private final PasswordSupplier passwordSupplier;
    private final KeyGenerator keySupplier;

    @Override
    public Map<String, Object> get() {
        Map<String, Object> payload = new HashMap<>();
        String firstName = firstNameSupplier.get();
        String lastName = lastNameSupplier.get();
        payload.put("FirstName", firstName);
        payload.put("LastName", lastName);
        String email = emailSupplier.apply(Pair.of(firstName, lastName));
        payload.put("Email", email);
        payload.put("Login", email);
        payload.put("PhoneNumber", phoneSupplier.get());
        payload.put("Password", passwordSupplier.get());
        payload.put("Key", keySupplier.get());
        payload.put("language", "ru");
        return payload;
    }
}
