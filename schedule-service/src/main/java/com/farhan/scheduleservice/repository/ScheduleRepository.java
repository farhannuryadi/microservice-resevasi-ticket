package com.farhan.scheduleservice.repository;

import com.farhan.scheduleservice.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query(value = "SELECT * FROM schedules WHERE schedule_id = :scheduleId", nativeQuery = true)
    ScheduleEntity findByScheduleId(Long scheduleId);
    @Query("SELECT s FROM ScheduleEntity s WHERE film_name = :filmName")
    List<ScheduleEntity> findByFilmName(String filmName);

    @Query(value = "SELECT studio_name FROM schedules WHERE schedule_id = :scheduleId", nativeQuery = true)
    String findStudioNameByScheduleId(Long scheduleId);

}
