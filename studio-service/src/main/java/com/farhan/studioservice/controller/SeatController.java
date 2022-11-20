package com.farhan.studioservice.controller;

import com.farhan.studioservice.dto.ResponseData;
import com.farhan.studioservice.dto.StudioRequest;
import com.farhan.studioservice.entity.SeatEntity;
import com.farhan.studioservice.entity.StudioEntity;
import com.farhan.studioservice.service.SeatService;
import com.farhan.studioservice.service.StudioService;
import com.farhan.studioservice.utility.ErrorParsingUtility;
import com.farhan.studioservice.utility.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.farhan.studioservice.utility.StatusMsg.ERROR_SERVER;
import static com.farhan.studioservice.utility.StatusMsg.SUCCSESS;

@RestController
@RequestMapping("/bioskop/api/seats")
public class SeatController {

    public static final Logger logger = LoggerFactory.getLogger(SeatController.class);

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping()
    public List<SeatEntity> findAll(){
        return seatService.findAll();
    }
}
