package com.farhan.studioservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private Long id;

    @Column(name = "studio_name", unique = true, nullable = false, length = 25)
    private String studioName;

    @Column(name = "max_seat")
    private Short maxSeat;

    @Column(name = "studio_status", nullable = false)
    private Boolean studioStatus;
}
