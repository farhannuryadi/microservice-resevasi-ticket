package com.farhan.filmservice.service.impl;

import com.farhan.filmservice.entity.FilmEntity;
import com.farhan.filmservice.repository.FilmRepository;
import com.farhan.filmservice.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmEntity save(FilmEntity filmEntity){
        return filmRepository.save(filmEntity);
    }

    @Override
    public FilmEntity findOne(String id){
        Optional<FilmEntity> film = filmRepository.findById(id);
        if (film.isEmpty()) {
            return null;
        }
        return film.get();
    }

    @Override
    public Iterable<FilmEntity> findAll(){
        return filmRepository.findAll();
    }

    @Override
    public void removeOne(String id){
        filmRepository.deleteById(id);
    }

    @Override
    public FilmEntity findByName(String name) {
        return filmRepository.findByName(name);
    }

    @Override
    public List<FilmEntity> findByStatus(Boolean status) {
        return filmRepository.findByStatus(status);
    }

    public Boolean findByFilmName(String filmName){
        FilmEntity filmEntity1 = filmRepository.findByFilmName(filmName);
        if (filmEntity1 == null){
            return false;
        }
        return true;
    }

    @Override
    public Boolean existsByFilmName(String filmName) {
        return  filmRepository.existsByFilmName(filmName);
    }
}
