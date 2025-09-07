package com.github.regyl.service.resetpassword;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class AsyncResetPasswordService {

    private static final int THREADS = 3;

    private final ResetPasswordService resetPasswordService;

    @PostConstruct
    public void init(){
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

        for (int i = 0; i < THREADS; i++) {
            executorService.submit(() -> {
               while (true) {
                   resetPasswordService.reset();
               }
            });
        }

    }
}
