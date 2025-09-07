package com.github.regyl.service.createaccount;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Component
public class CreateAccountService {

    private final ThreadLocal<RestTemplate> restTemplateThreadLocal;
    private final PayloadBuilderService payloadBuilderService;


    public CreateAccountService(PayloadBuilderService payloadBuilderService) {
        this.payloadBuilderService = payloadBuilderService;
        restTemplateThreadLocal = new ThreadLocal<>();
    }


    public void createAccount() {
        Map<String, Object> payload = payloadBuilderService.get();
        RestTemplate restTemplate = restTemplateThreadLocal.get();
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            restTemplateThreadLocal.set(restTemplate);
        }

        try {
            ResponseEntity<String> response = restTemplateThreadLocal.get().postForEntity("https://geba-qi.co/api/CreateUserAndAccounts", payload, String.class);
            String msg = String.format("Status: %s. For entity :%s. Body: %s", response.getStatusCode(), payload, response.getBody());
            System.out.println(msg);
        } catch (HttpClientErrorException.TooManyRequests e) {
            System.err.println(e.getMessage());
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
