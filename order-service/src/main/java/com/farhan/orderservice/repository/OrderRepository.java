package com.farhan.orderservice.repository;

import com.farhan.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUsername(String username);

    @Query(value = "SELECT order_id FROM orders WHERE schedule_id = :scheduleId AND username = :username", nativeQuery = true)
    Long findOrderIdByScheduleIdAndUsername(Long scheduleId, String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO orders (date_order, quantity, total_price, schedule_id, username)\n" +
            "VALUES (CURRENT_TIMESTAMP, :quantity, :totalPrice, :scheduleId, :username)", nativeQuery = true)
    void createOrder(int quantity, BigDecimal totalPrice, Long scheduleId, String username);
}
