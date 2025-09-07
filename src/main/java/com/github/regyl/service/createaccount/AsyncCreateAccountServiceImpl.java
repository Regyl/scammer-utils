package com.github.regyl.service.createaccount;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "scammers.gebaqi.enabled.create-account", havingValue = "true")
public class AsyncCreateAccountServiceImpl implements Runnable {

    private final CreateAccountServiceImpl createAccountService;
    private static final int THREADS = 10;

    @Override
    @PostConstruct
    public void run() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(THREADS)) {

            for (int i = 0; i < THREADS; i++) {
                executorService.submit(() -> {
                    while (true) {
                        long start = System.nanoTime();
                        createAccountService.createAccount();
                        long end = System.nanoTime();
//                    System.out.println("Spent seconds: " + TimeUnit.NANOSECONDS.toMillis(end-start));
                    }
                });
            }
        }
    }
}
