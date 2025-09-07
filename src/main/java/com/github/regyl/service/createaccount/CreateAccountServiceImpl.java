package com.github.regyl.service.createaccount;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j
@Component
@ConditionalOnBean(AsyncCreateAccountServiceImpl.class)
public class CreateAccountServiceImpl implements Runnable {

    private final ThreadLocal<RestTemplate> restTemplateThreadLocal;
    private final PayloadBuilderServiceImpl payloadBuilderService;


    public CreateAccountServiceImpl(PayloadBuilderServiceImpl payloadBuilderService) {
        this.payloadBuilderService = payloadBuilderService;
        restTemplateThreadLocal = new ThreadLocal<>();
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        Map<String, Object> payload = payloadBuilderService.get();
        RestTemplate restTemplate = restTemplateThreadLocal.get();
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            restTemplateThreadLocal.set(restTemplate);
        }

        try {
            ResponseEntity<String> response = restTemplateThreadLocal.get().postForEntity("https://geba-qi.co/api/CreateUserAndAccounts", payload, String.class);
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
