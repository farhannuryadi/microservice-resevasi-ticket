package com.farhan.orderservice.service.impl;

import com.farhan.orderservice.dto.SeatAvailabelResponse;
import com.farhan.orderservice.dto.SeatReponse;
import com.farhan.orderservice.entity.OrderDetailEntity;
import com.farhan.orderservice.entity.OrderEntity;
import com.farhan.orderservice.repository.OrderDetailRepository;
import com.farhan.orderservice.repository.OrderRepository;
import com.farhan.orderservice.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public OrderDetailEntity save(OrderDetailEntity orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetailEntity> findByOrder(OrderEntity order) {
        return orderDetailRepository.findByOrder(order);
    }

    @Override
    public void createOrderDetail(List<String> seats, Long scheduleId, String username){
        Long orderId = orderRepository.findOrderIdByScheduleIdAndUsername(scheduleId, username);
        String studioName = webClientBuilder.build().get()
                .uri("http://schedule-service/bioskop/api/schedules/studioName/"+scheduleId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (orderId == null || studioName == null) throw new IllegalStateException("gagal membuat order");
        seats.forEach(seatName -> {
            orderDetailRepository.createOrderDetail(seatName, scheduleId, studioName, orderId, username);
        });
    }

    @Override
    public SeatAvailabelResponse seatAvilable(Long scheduleId) {
        SeatAvailabelResponse seatAvailabelResponse = new SeatAvailabelResponse();
        List<String> seatOrder = orderDetailRepository.findSeatByScheduleId(scheduleId);
        SeatReponse[] resultSeat = webClientBuilder.build().get()
                .uri("http://studio-service/bioskop/api/seats")
                .retrieve()
                .bodyToMono(SeatReponse[].class)
                .block();
        List<String> list = Arrays.stream(resultSeat).map(SeatReponse::getSeatName).collect(Collectors.toList());
        seatOrder.forEach(list::remove);
//        list.forEach(System.out::println);
        seatAvailabelResponse.setSeatName(list);
        return seatAvailabelResponse;
    }
}
