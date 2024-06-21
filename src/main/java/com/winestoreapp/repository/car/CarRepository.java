package com.winestoreapp.repository.car;

import com.winestoreapp.model.OrderDeliveryInformation;
import com.winestoreapp.model.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
