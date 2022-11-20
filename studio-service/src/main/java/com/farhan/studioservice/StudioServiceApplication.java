package com.farhan.studioservice;

import com.farhan.studioservice.entity.SeatEntity;
import com.farhan.studioservice.entity.StudioEntity;
import com.farhan.studioservice.repository.SeatRepository;
import com.farhan.studioservice.repository.StudioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class StudioServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudioServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(StudioRepository studioRepository, SeatRepository seatRepository) {
        return args -> {
            StudioEntity studioEntity = new StudioEntity(1L, "Studio A", (short) 10, true);
            StudioEntity studioEntity1 = new StudioEntity(2L, "Studio B", (short) 10, true);

            studioRepository.save(studioEntity);
            studioRepository.save(studioEntity1);

            SeatEntity seatEntity1 = new SeatEntity(1L, "01A");
            SeatEntity seatEntity2 = new SeatEntity(2L, "02A");
            SeatEntity seatEntity3 = new SeatEntity(3L, "03A");
            SeatEntity seatEntity4 = new SeatEntity(4L, "04A");
            SeatEntity seatEntity5 = new SeatEntity(5L, "05A");
            SeatEntity seatEntity6 = new SeatEntity(6L, "01B");
            SeatEntity seatEntity7 = new SeatEntity(7L, "02B");
            SeatEntity seatEntity8 = new SeatEntity(8L, "03B");
            SeatEntity seatEntity9 = new SeatEntity(9L, "04B");
            SeatEntity seatEntity10 = new SeatEntity(10L, "05B");

            seatRepository.save(seatEntity1);
            seatRepository.save(seatEntity2);
            seatRepository.save(seatEntity3);
            seatRepository.save(seatEntity4);
            seatRepository.save(seatEntity5);
            seatRepository.save(seatEntity6);
            seatRepository.save(seatEntity7);
            seatRepository.save(seatEntity8);
            seatRepository.save(seatEntity9);
            seatRepository.save(seatEntity10);
        };
    }
}
