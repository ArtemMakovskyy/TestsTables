package com.winestoreapp.controller;

import com.winestoreapp.model.Order;
import com.winestoreapp.model.OrderDeliveryInformation;
import com.winestoreapp.model.OrderPaymentStatus;
import com.winestoreapp.model.PurchaseObject;
import com.winestoreapp.model.Review;
import com.winestoreapp.model.ShoppingCard;
import com.winestoreapp.model.User;
import com.winestoreapp.model.Wine;
import com.winestoreapp.model.WineColor;
import com.winestoreapp.model.WineType;
import com.winestoreapp.model.car.Car;
import com.winestoreapp.repository.OrderDeliveryInformationRepository;
import com.winestoreapp.repository.OrderRepository;
import com.winestoreapp.repository.PurchaseObjectRepository;
import com.winestoreapp.repository.ReviewRepository;
import com.winestoreapp.repository.ShoppingCardRepository;
import com.winestoreapp.repository.UserRepository;
import com.winestoreapp.repository.WineRepository;
import com.winestoreapp.service.CarService;
import com.winestoreapp.service.WineServiceTest;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    @GetMapping()
    public Car car() {
        carService.createCarAndManufacturer1();
        carService.createCarAndManufacturer2();
//        return carService.createCarAndManufacturer2();
        System.out.println("car");
        return new Car();
    }
}
