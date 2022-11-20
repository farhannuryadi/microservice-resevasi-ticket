package com.farhan.scheduleservice.service;

import com.farhan.scheduleservice.dto.ScheduleForOrderResponse;
import com.farhan.scheduleservice.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleService {

    ScheduleEntity save(ScheduleEntity scheduleEntity);
    ScheduleEntity findOne(Long id);
    Iterable<ScheduleEntity> findAll();
    void removeOne(Long id);
    List<ScheduleEntity> findByFilmName(String filmName);
    String findStudioNameByScheduleId(Long scheduleId);

    ScheduleForOrderResponse findByScheduleId(Long scheduleId);
}
