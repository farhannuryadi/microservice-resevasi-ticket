package com.farhan.orderservice.repository;

import com.farhan.orderservice.entity.OrderDetailEntity;
import com.farhan.orderservice.entity.OrderEntity;
import com.farhan.orderservice.entity.ScheduleSeatStudioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, ScheduleSeatStudioId> {

    @Query("SELECT od FROM OrderDetailEntity od WHERE od.order = :order")
    List<OrderDetailEntity> findByOrder(OrderEntity order);

    @Query(value = "SELECT seat_name FROM order_detail WHERE schedule_id = :scheduleId", nativeQuery = true)
    List<String> findSeatByScheduleId(Long scheduleId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO order_detail (seat_name, schedule_id, studio_name, order_id, username)\n" +
            "VALUES (:seatName, :scheduleId, :studioName, :orderId, :username)", nativeQuery = true)
    void createOrderDetail(String seatName, Long scheduleId, String studioName, Long orderId, String username);
}