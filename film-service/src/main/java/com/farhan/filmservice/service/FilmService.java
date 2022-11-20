package com.farhan.filmservice.service;

import com.farhan.filmservice.entity.FilmEntity;

import java.util.List;

public interface FilmService {
    FilmEntity save(FilmEntity filmEntity);
    FilmEntity findOne(String id);
    Iterable<FilmEntity> findAll();
    void removeOne(String id);
    FilmEntity findByName(String name);
    List<FilmEntity> findByStatus(Boolean status);
    Boolean findByFilmName(String filmName);

    Boolean existsByFilmName(String filmName);
}
