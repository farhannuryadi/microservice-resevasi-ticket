package com.farhan.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScheduleForOrderResponse {

    Long ScheduleId;
    BigDecimal price;
}
