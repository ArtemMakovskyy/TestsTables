package com.winestoreapp.service;

import com.winestoreapp.model.OrderDeliveryInformation;
import com.winestoreapp.repository.OrderDeliveryInformationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderDeliveryInformationServiceImpl {
    private final OrderDeliveryInformationRepository orderDeliveryInformationRepository;
}
