package com.farhan.scheduleservice.service.impl;

import com.farhan.scheduleservice.dto.ScheduleForOrderResponse;
import com.farhan.scheduleservice.entity.ScheduleEntity;
import com.farhan.scheduleservice.repository.ScheduleRepository;
import com.farhan.scheduleservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public ScheduleEntity save(ScheduleEntity scheduleEntity) {
        Boolean resultFilm = webClientBuilder.build().get()
                .uri("http://film-service/bioskop/api/films/name/"+scheduleEntity.getFilmName())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        Boolean resultStudio = webClientBuilder.build().get()
                .uri("http://studio-service/bioskop/api/studios/name/"+scheduleEntity.getStudioName())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();


        if (!resultFilm || !resultStudio){
            throw new IllegalStateException("Film "+scheduleEntity.getFilmName()+" atau "+ scheduleEntity.getStudioName() +" belum ada, tidak dapat ditambahkan ke schedule");
        }
        return scheduleRepository.save(scheduleEntity);
    }

    @Override
    public ScheduleEntity findOne(Long id) {
        Optional<ScheduleEntity> scheduleEntity = scheduleRepository.findById(id);
        if (scheduleEntity.isEmpty()) {
            return null;
        }
        return scheduleEntity.get();
    }

    @Override
    public Iterable<ScheduleEntity> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public void removeOne(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<ScheduleEntity> findByFilmName(String filmName) {
        return scheduleRepository.findByFilmName(filmName);
    }

    @Override
    public String findStudioNameByScheduleId(Long scheduleId) {
        return scheduleRepository.findStudioNameByScheduleId(scheduleId);
    }

    @Override
    public ScheduleForOrderResponse findByScheduleId(Long scheduleId) {
        ScheduleEntity scheduleEntity = scheduleRepository.findByScheduleId(scheduleId);
        if (scheduleEntity != null) {
            return ScheduleForOrderResponse.builder()
                    .ScheduleId(scheduleEntity.getId())
                    .price(scheduleEntity.getPrice())
                    .build();
        }
        return null;
    }
}

