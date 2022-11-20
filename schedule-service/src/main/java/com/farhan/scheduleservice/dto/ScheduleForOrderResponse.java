package com.farhan.scheduleservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ScheduleForOrderResponse {
    Long ScheduleId;
    BigDecimal price;
}
