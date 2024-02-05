package com.winestoreapp.repository;

import com.winestoreapp.model.OrderDeliveryInformation;
import com.winestoreapp.model.PurchaseObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDeliveryInformationRepository extends JpaRepository<OrderDeliveryInformation, Long> {

//    Optional<PurchaseObject> findById(Long id);

}
