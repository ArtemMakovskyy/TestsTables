package com.winestoreapp.service;

import com.winestoreapp.model.car.Car;
import com.winestoreapp.model.car.CarModel;
import com.winestoreapp.model.car.Manufacturer;
import com.winestoreapp.model.car.SerialNumber;
import com.winestoreapp.repository.car.CarRepository;
import com.winestoreapp.repository.car.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final ManufacturerRepository manufacturerRepository;


    public Car createCarAndManufacturer1() {
        Car car = new Car();
        car.setName("some name");
        car.setManufacturer(new Manufacturer("Audi"));
        car.setCarModel(new CarModel("A8"));
        car.setSerialNumber(new SerialNumber("ABC1123dfsgsd"));
        final Car save = carRepository.save(car);
        System.out.println(save);
        return save;
    }

    public Car createCarAndManufacturer2() {
        final Manufacturer manufacturer = manufacturerRepository
                .findById(1L)
                .orElse(new Manufacturer("new man"));
        Car car = new Car();
        car.setName("some name 2");

        car.setManufacturer(manufacturerRepository
                .findById(1L)
                .orElse(new Manufacturer("new man")));

//        car2.setManufacturer(new Manufacturer("sdf"));
        car.setCarModel(new CarModel("A9"));
        car.setSerialNumber(new SerialNumber("ABC1123dfsgsd2"));
        final Car save2 = carRepository.save(car);
//        System.out.println(save2);
//        save2.setManufacturer(manufacturer);
//        System.out.println(save2);
//        carRepository.save(save2);
        return save2;
    }
}
