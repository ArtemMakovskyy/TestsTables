package com.winestoreapp.transaction;

import com.winestoreapp.repository.TCarRepository;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class TCarService {
    private static int calculate = 0;
    private final TCarRepository carRepository;

    @SneakyThrows
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void increaseCarPrice(Long carId) {
        calculate ++;
        var car = carRepository.findById(carId).orElseThrow();
        log.info(calculate + " =======================  ");
        log.info("Price before increasing: {}", car.getPrice());

        car.setPrice(car.getPrice().add(BigDecimal.TEN));
        carRepository.save(car);

        Thread.sleep(300);

        log.info("Price after increasing: {}", car.getPrice());
    }

    @SneakyThrows
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCarPrice(Long carId) {
        var car = carRepository.findById(carId).orElseThrow();
        log.info("1. Read car price: {}", car.getPrice());

        Thread.sleep(200);
         car = carRepository.findById(carId).orElseThrow();
        log.info("2. Read car price: after 200 ms {}", car.getPrice());

        Thread.sleep(200);
        car = carRepository.findById(carId).orElseThrow();
        log.info("3. Read car price: after 400 ms {}", car.getPrice());

        Thread.sleep(200);
        car = carRepository.findById(carId).orElseThrow();
        log.info("4. Read car price: after 600 ms {}", car.getPrice());

    }

    @PostConstruct
    public void init(){
        carRepository.save(new TCar("Audy",BigDecimal.valueOf(3000L)));
        carRepository.save(new TCar("Lada",BigDecimal.valueOf(5000L)));
        carRepository.save(new TCar("Mazda",BigDecimal.valueOf(4000L)));
    }
}
