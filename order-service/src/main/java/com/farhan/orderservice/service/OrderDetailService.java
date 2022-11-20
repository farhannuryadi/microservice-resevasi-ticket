package com.farhan.orderservice.service;

import com.farhan.orderservice.dto.SeatAvailabelResponse;
import com.farhan.orderservice.entity.OrderDetailEntity;
import com.farhan.orderservice.entity.OrderEntity;

import java.util.List;


public interface OrderDetailService {

    OrderDetailEntity save(OrderDetailEntity orderDetail);
    List<OrderDetailEntity> findByOrder(OrderEntity order);
    void createOrderDetail(List<String> seats, Long scheduleId, String username);
    SeatAvailabelResponse seatAvilable(Long scheduleId);
}
