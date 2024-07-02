package com.winestoreapp.transaction;

import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionTCarService {
    private final TCarService carService;

    public void readAndIncreaseCarPrice() {
        var firstIncrease = CompletableFuture.runAsync(() -> {
            log.info("Start transaction");
            carService.increaseCarPrice(1L);
            log.info("End transaction");
        });
        var secondIncrease = CompletableFuture.runAsync(() -> {
            carService.readCarPrice(1L);
        });
        CompletableFuture.allOf(firstIncrease,secondIncrease)
                .join();
    }
}
