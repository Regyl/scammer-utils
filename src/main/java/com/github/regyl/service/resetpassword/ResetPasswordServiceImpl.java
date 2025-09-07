package com.github.regyl.service.resetpassword;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnBean(AsyncResetPasswordService.class)
public class ResetPasswordServiceImpl {

    private static final ThreadLocal<RestTemplate> restTemplateThreadLocal;

    static {
        restTemplateThreadLocal = new ThreadLocal<>();
    }

    private final ExistEmailSupplierImpl existEmailSupplier;

    public void reset() {
        long start = System.nanoTime();
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
            long end = System.nanoTime();
            String msg = String.format("Status: %s. Took %d ms. For entity: %s. Body: %s", response.getStatusCode(), TimeUnit.NANOSECONDS.toMillis(end-start), payload, response.getBody());
            log.info(msg);
        } catch (HttpClientErrorException.TooManyRequests e) {
            log.error("Got too many requests. Pausing for 15 minutes");
            LockSupport.parkNanos(TimeUnit.MINUTES.toNanos(15));
        } catch (Exception e) {
            log.error("Error", e);
        }
    }
}
