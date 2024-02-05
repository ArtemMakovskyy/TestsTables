package com.winestoreapp.repository;

import com.winestoreapp.model.Order;
import com.winestoreapp.model.OrderDeliveryInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

//    Optional<PurchaseObject> findById(Long id);

}
