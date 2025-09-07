package com.github.regyl.service.createaccount;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AsyncCreateAccountService {

    private final CreateAccountService createAccountService;
    private static final int THREADS = 10;

    @PostConstruct
    public void init(){
        for (int i = 0; i < THREADS; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    long start = System.nanoTime();
                    createAccountService.createAccount();
                    long end = System.nanoTime();
//                    System.out.println("Spent seconds: " + TimeUnit.NANOSECONDS.toSeconds(end-start));
                }
            });
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.start();
        }
    }
}
