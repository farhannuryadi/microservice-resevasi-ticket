package com.farhan.orderservice.service;


import com.farhan.orderservice.dto.SeatAvailabelResponse;
import com.farhan.orderservice.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    List<OrderEntity> findByUsername(String username);
    void createOrder(Long scheduleId, String username, List<String> seats);
}
