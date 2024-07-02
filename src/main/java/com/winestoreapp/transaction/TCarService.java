package com.winestoreapp.transaction;

import com.winestoreapp.repository.TCarRepository;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class TCarService {
    {
        System.out.println("https://www.youtube.com/watch?v=ovas9OCVfqo&t=1226s");
    }

    private static int calculate = 0;
    private final TCarRepository carRepository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            readOnly = true
    ,propagation = Propagation.REQUIRED)
    public void readOnlyTransactional() {
        var car = carRepository.findById(1L).orElseThrow();
        log.info("Start transaction? car price: {}", car.getPrice());
        car.setPrice(BigDecimal.ONE);
        carRepository.save(car);

        car = carRepository.findById(1L).orElseThrow();
        log.info("End transaction? car price: {}", car.getPrice());
    }


    @SneakyThrows
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void increaseCarPrice(Long carId) {
        calculate++;
        var car = carRepository.findById(carId).orElseThrow();
        log.info(calculate + " =======================  ");
        log.info("Price before increasing: {}", car.getPrice());

        car.setPrice(car.getPrice().add(BigDecimal.TEN));
        carRepository.save(car);

        Thread.sleep(300);

        log.info("Price after increasing: {}", car.getPrice());
    }

    public void readCarPrice(Long carId) {
//        readCarPriceFirstLesson(carId);
//        readCarPriceSecondLesson(carId);
        readCarPriceThirdLesson(carId);
    }

    @SneakyThrows
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCarPriceFirstLesson(Long carId) {
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

    @SneakyThrows
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCarPriceSecondLesson(Long carId) {
        var sql = "SELECT price FROM tcar WHERE id = " + carId;
        var price = jdbcTemplate.queryForObject(sql, String.class);

        log.info("1. Read car price: {}", price);

        Thread.sleep(200);
        price = jdbcTemplate.queryForObject(sql, String.class);
        log.info("200. Read car price: {}", price);

        Thread.sleep(200);
        price = jdbcTemplate.queryForObject(sql, String.class);
        log.info("400. Read car price: {}", price);

        Thread.sleep(200);
        price = jdbcTemplate.queryForObject(sql, String.class);
        log.info("600. Read car price: {}", price);
    }

    @SneakyThrows
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void readCarPriceThirdLesson(Long carId) {
        var sql = "SELECT price FROM tcar WHERE id = " + carId;
        var price = jdbcTemplate.queryForObject(sql, String.class);

        log.info("1. Read car price: {}", price);

        Thread.sleep(200);
        price = jdbcTemplate.queryForObject(sql, String.class);
        log.info("200. Read car price: {}", price);

        Thread.sleep(200);
        price = jdbcTemplate.queryForObject(sql, String.class);
        log.info("400. Read car price: {}", price);

        Thread.sleep(200);
        price = jdbcTemplate.queryForObject(sql, String.class);
        log.info("600. Read car price: {}", price);
    }

    @PostConstruct
    public void init() {
        carRepository.save(new TCar("Audy", BigDecimal.valueOf(3000L)));
        carRepository.save(new TCar("Lada", BigDecimal.valueOf(5000L)));
        carRepository.save(new TCar("Mazda", BigDecimal.valueOf(4000L)));
    }
}
