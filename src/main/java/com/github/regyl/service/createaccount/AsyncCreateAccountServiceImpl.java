package com.github.regyl.service.createaccount;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "scammers.gebaqi.create-account.enabled", havingValue = "true")
public class AsyncCreateAccountServiceImpl implements Runnable {

    private final CreateAccountServiceImpl createAccountService;
    private static final int THREADS = 15;

    @Async
    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        log.info("Create account spam starting...");
        try (ExecutorService executorService = Executors.newFixedThreadPool(THREADS)) {

            for (int i = 0; i < THREADS; i++) {
                executorService.submit(() -> {
                    while (true) {
                        if (Thread.currentThread().isInterrupted()) {
                            log.warn("thread is interrupted");
                            return;
                        }

                        createAccountService.run();
                    }
                });
            }
        }
    }
}
