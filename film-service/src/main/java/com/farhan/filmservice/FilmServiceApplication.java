package com.farhan.filmservice;

import com.farhan.filmservice.entity.FilmEntity;
import com.farhan.filmservice.repository.FilmRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class FilmServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(FilmRepository filmRepository) {
        return args -> {
            FilmEntity filmEntity = new FilmEntity("idn1161122", "Iron Man", true);
            FilmEntity filmEntity1 = new FilmEntity("idn2161122", "Hulk", true);

            filmRepository.save(filmEntity);
            filmRepository.save(filmEntity1);
        };
    }
}
