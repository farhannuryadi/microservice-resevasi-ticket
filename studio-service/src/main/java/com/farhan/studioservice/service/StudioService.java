package com.farhan.studioservice.service;


import com.farhan.studioservice.entity.StudioEntity;

public interface StudioService {

    StudioEntity save(StudioEntity studioEntity);
    StudioEntity findOne(Long id);
    Iterable<StudioEntity> findAll();
    void removeOne(Long id);

    Boolean findByStudioName(String studioName);
}
