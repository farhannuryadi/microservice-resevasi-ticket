package com.farhan.studioservice.repository;

import com.farhan.studioservice.entity.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<StudioEntity, Long> {

    @Query(value = "SELECT seat_id FROM seats WHERE seat_name = :studioName", nativeQuery = true)
    Long findStudioIdByName(String studioName);

    Boolean existsByStudioName(String studioName);
}