package com.farhan.orderservice.service.impl;

import com.farhan.orderservice.dto.ScheduleForOrderResponse;
import com.farhan.orderservice.dto.SeatAvailabelResponse;
import com.farhan.orderservice.dto.SeatReponse;
import com.farhan.orderservice.dto.UserResponse;
import com.farhan.orderservice.entity.OrderEntity;
import com.farhan.orderservice.repository.OrderRepository;
import com.farhan.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;


    @Override
    public List<OrderEntity> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    @Override
    public void createOrder(Long scheduleId, String username, List<String> seats){
        UserResponse userResponse = webClientBuilder.build().get()
                .uri("http://user-service/bioskop/api/users/find/"+username)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (userResponse == null)throw new IllegalStateException("user dengan username : "+username+
                "tidak ada, gagal membuat order");

        ScheduleForOrderResponse schedule = webClientBuilder.build().get()
                .uri("http://schedule-service/bioskop/api/schedules/getSchedule/"+scheduleId)
                .retrieve()
                .bodyToMono(ScheduleForOrderResponse.class)
                .block();
        if (schedule == null) throw new IllegalStateException("schedule id : "+schedule.getScheduleId()+
                "tidak ada, gagal membuat order");

        int quantity = seats.size();
        BigDecimal totalPrice = schedule.getPrice();
        totalPrice = totalPrice.multiply(BigDecimal.valueOf(quantity));
        orderRepository.createOrder(quantity, totalPrice, scheduleId, username);
    }
}
