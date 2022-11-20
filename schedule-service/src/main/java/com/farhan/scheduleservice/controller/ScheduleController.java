package com.farhan.scheduleservice.controller;

import com.farhan.scheduleservice.dto.ResponseData;
import com.farhan.scheduleservice.dto.ScheduleForOrderResponse;
import com.farhan.scheduleservice.dto.ScheduleRequest;
import com.farhan.scheduleservice.dto.SearchRequest;
import com.farhan.scheduleservice.entity.ScheduleEntity;
import com.farhan.scheduleservice.service.ScheduleService;
import com.farhan.scheduleservice.utility.ErrorParsingUtility;
import com.farhan.scheduleservice.utility.StatusCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;

import static com.farhan.scheduleservice.utility.StatusMsg.*;


@RestController
@RequestMapping("/bioskop/api/schedules")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", maxAge = 3600)
//@PreAuthorize("hasRole('ADMIN')")
//@Tag(name = "Schedule", description = "Operation about schedule")
public class ScheduleController {

    public static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    private final ScheduleService scheduleService;

//    private final FilmService filmService;
//
//    private final StudioService studioService;

//    @Autowired
//    public ScheduleController(ScheduleService scheduleService) {
//        this.scheduleService = scheduleService;
//    }

//    @Operation(summary = "Add a new schdedule with id film")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Request Error Message"),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @PostMapping("/with/id")
    public ResponseEntity<ResponseData<ScheduleEntity>> create(@RequestBody ScheduleRequest scheduleRequest, Errors errors){
        ResponseData<ScheduleEntity> responseData = new ResponseData<>();
        if (errors.hasErrors())
        {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn(REQUEST_INVALID, ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setShowDate(scheduleRequest.getShowDate());
            scheduleEntity.setStartTime(scheduleRequest.getStartTime());
            scheduleEntity.setEndTime(scheduleRequest.getEndTime());
            scheduleEntity.setPrice(scheduleRequest.getPrice());
            scheduleEntity.setFilmName(scheduleRequest.getFilmName());
            scheduleEntity.setStudioName(scheduleRequest.getStudioName());

            responseData.setData(scheduleService.save(scheduleEntity));
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            logger.info("sukses create schedule id : {}, for film : {}, in studio : {}", scheduleEntity.getId(),
                    scheduleEntity.getFilmName(), scheduleEntity.getStudioName());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Get a schedule by its id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<ScheduleEntity>> findOne(@PathVariable("id") Long id){
        ResponseData<ScheduleEntity> responseData = new ResponseData<>();
        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(scheduleService.findOne(id));
            logger.info("sukses get schedule id : {}", id);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Get all schedules")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<ScheduleEntity>>> findAll(){
        ResponseData<Iterable<ScheduleEntity>> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(scheduleService.findAll());
            logger.info("sukses get all schedule");
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Update a schedule")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Request Error Message"),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @PutMapping("/update/with/id")
    public ResponseEntity<ResponseData<ScheduleEntity>> update(@RequestBody ScheduleRequest scheduleRequest, Errors errors){
        ResponseData<ScheduleEntity> responseData = new ResponseData<>();
        if (errors.hasErrors())
        {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn(REQUEST_INVALID, ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setShowDate(scheduleRequest.getShowDate());
            scheduleEntity.setStartTime(scheduleRequest.getStartTime());
            scheduleEntity.setEndTime(scheduleRequest.getEndTime());
            scheduleEntity.setPrice(scheduleRequest.getPrice());
            scheduleEntity.setFilmName(scheduleRequest.getFilmName());
            scheduleEntity.setStudioName(scheduleRequest.getStudioName());

            responseData.setData(scheduleService.save(scheduleEntity));
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            logger.info("sukses create schedule id : {}, for film : {}, in studio : {}", scheduleEntity.getId(),
                    scheduleEntity.getFilmName(), scheduleEntity.getStudioName());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Delete a schedule by its id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses"),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> removeOne(@PathVariable("id") Long id){
        ResponseData<String> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            scheduleService.removeOne(id);
            logger.info("delete schedule with id : {}", id);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Get a schedule by its film name")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Request Error Message"),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
//    @PreAuthorize("hasRole('USER')")
    @PostMapping("/film/name")
    public ResponseEntity<ResponseData<List<ScheduleEntity>>> findFilmName(@RequestBody SearchRequest filmName, Errors errors){
        ResponseData<List<ScheduleEntity>> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("error request : {}", ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(scheduleService.findByFilmName(filmName.getSearchKey()));
            logger.info("find schedule with film name : {}", filmName.getSearchKey());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @GetMapping("/studioName/{scheduleId}")
    public String getStudioName(@PathVariable("scheduleId") Long scheduleId){
        return scheduleService.findStudioNameByScheduleId(scheduleId);
    }

    @GetMapping("/getSchedule/{scheduleId}")
    public ScheduleForOrderResponse getSchedule(@PathVariable("scheduleId") Long scheduleId){
        return scheduleService.findByScheduleId(scheduleId);
    }
}
