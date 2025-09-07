package com.github.regyl.service.resetpassword;

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
@ConditionalOnProperty(value = "scammers.gebaqi.reset-password.enabled", havingValue = "true")
public class AsyncResetPasswordService implements Runnable {

    private static final int THREADS = 5;

    private final ResetPasswordServiceImpl resetPasswordService;

    @Async
    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        log.info("Reset password spam starting...");
        try (ExecutorService executorService = Executors.newFixedThreadPool(THREADS)) {

            for (int i = 0; i < THREADS; i++) {
                executorService.submit(() -> {
                    while (true) {
                        if (Thread.currentThread().isInterrupted()) {
                            log.warn("thread is interrupted");
                            return;
                        }

                        resetPasswordService.reset();
                    }
                });
            }
        }
    }
}
