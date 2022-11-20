package com.farhan.studioservice.service;

import com.farhan.studioservice.entity.SeatEntity;

import java.util.List;

public interface SeatService {

    List<SeatEntity> findAll();
//    List<String> findSeatAvailable(Long scheduleId);
}
