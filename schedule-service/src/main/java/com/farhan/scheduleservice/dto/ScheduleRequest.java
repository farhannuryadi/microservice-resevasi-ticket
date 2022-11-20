package com.farhan.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {

    private String filmName;
    @NotEmpty
    private String studioName;
    @NotEmpty
    private LocalDate showDate;
    @NotEmpty
    private LocalTime startTime;
    @NotEmpty
    private LocalTime endTime;
    @NotEmpty
    private BigDecimal price;
}
