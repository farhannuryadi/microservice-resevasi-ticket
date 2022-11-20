package com.farhan.studioservice.controller;

import com.farhan.studioservice.dto.ResponseData;
import com.farhan.studioservice.dto.StudioRequest;
import com.farhan.studioservice.entity.StudioEntity;
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
import javax.websocket.server.PathParam;

import static com.farhan.studioservice.utility.StatusMsg.*;

@RestController
@RequestMapping("/bioskop/api/studios")
//@CrossOrigin(origins = "*", maxAge = 3600)
//@PreAuthorize("hasRole('ADMIN')")
//@Tag(name = "Studio", description = "Operation about studio")
public class StudioController {

    public static final Logger logger = LoggerFactory.getLogger(StudioController.class);

    private final StudioService studioService;

    @Autowired
    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

//    @Operation(summary = "Add a new studio")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = StudioEntity.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Request Error Message"),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @PostMapping
    public ResponseEntity<ResponseData<StudioEntity>> create(@Valid @RequestBody StudioRequest studioRequest, Errors errors){

        ResponseData<StudioEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("error request : {}", ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try{
            StudioEntity studioEntity = new StudioEntity(studioRequest.getId(), studioRequest.getStudioName(),
                    studioRequest.getMaxSeat(), studioRequest.getStudioStatus());
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(studioService.save(studioEntity));
            logger.info("create studio : {}", studioEntity.getStudioName());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Get a studio by its id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = StudioEntity.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<StudioEntity>> findOne(@PathVariable("id") Long id){
        ResponseData<StudioEntity> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(studioService.findOne(id));
            logger.info("call find studio with id : {}", id);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Get all studios")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = StudioEntity.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<StudioEntity>>> findAll(){
        ResponseData<Iterable<StudioEntity>> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(studioService.findAll());
            logger.info("call find all studio");
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Update a studio")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = StudioEntity.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Request Error Message"),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @PutMapping
    public ResponseEntity<ResponseData<StudioEntity>> update(@Valid @RequestBody StudioRequest studioRequest, Errors errors){

        ResponseData<StudioEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("error request : {}", ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try{
            StudioEntity studioEntity = new StudioEntity(studioRequest.getId(), studioRequest.getStudioName(),
                    studioRequest.getMaxSeat(), studioRequest.getStudioStatus());
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(studioService.save(studioEntity));
            logger.info("update studio name : {}", studioEntity.getStudioName());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Delete a studio by its id")
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
            studioService.removeOne(id);
            logger.info("delete studio id : {}", id);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @GetMapping("/name/{studioName}")
    public Boolean findByStudioName(@PathVariable("studioName") String studioName){
        return studioService.findByStudioName(studioName);
    }
}
