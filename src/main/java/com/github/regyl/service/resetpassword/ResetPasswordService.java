package com.github.regyl.service.resetpassword;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Component
@RequiredArgsConstructor
public class ResetPasswordService {

    private static final ThreadLocal<RestTemplate> restTemplateThreadLocal;

    static {
        restTemplateThreadLocal = new ThreadLocal<>();
    }

    private final ExistEmailSupplierImpl existEmailSupplier;

    public void reset() {
        String email = existEmailSupplier.get();
        Map<String, Object> payload = new HashMap<>();
        payload.put("Login", email);

        RestTemplate restTemplate = restTemplateThreadLocal.get();
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            restTemplateThreadLocal.set(restTemplate);
        }

        try {
        ResponseEntity<String> response = restTemplateThreadLocal.get().postForEntity("https://geba-qi.co/api/ResetPassword", payload, String.class);
        String msg = String.format("Status: %s. For entity :%s. Body: %s", response.getStatusCode(), payload, response.getBody());
        System.out.println(msg);
        } catch (HttpClientErrorException.TooManyRequests e) {
            System.err.println(e.getMessage());
            LockSupport.parkNanos(TimeUnit.MINUTES.toNanos(15));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
