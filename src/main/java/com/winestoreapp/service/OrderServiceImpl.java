package com.winestoreapp.service;

import com.winestoreapp.model.Order;
import com.winestoreapp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl {
   private final OrderRepository orderRepository;

   public Order save(){
// TODO: 05.02.2024
       return new Order();
   }
}
