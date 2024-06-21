package com.winestoreapp.repository.car;

import com.winestoreapp.model.car.Car;
import com.winestoreapp.model.car.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
