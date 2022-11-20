package com.farhan.scheduleservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schedules")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ScheduleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "film_name", nullable = false)
    private String filmName;

    @Column(name = "studio_name", nullable = false)
    private String studioName;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @JsonFormat(pattern = "HH:mm", timezone = "GMT+7")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm", timezone = "GMT+7")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private BigDecimal price;
}
