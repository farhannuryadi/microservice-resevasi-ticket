package com.farhan.orderservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleSeatStudioId implements Serializable {

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "seat_name")
    private String seatName;

    @Column(name = "studio_name")
    private String studioName;
}
