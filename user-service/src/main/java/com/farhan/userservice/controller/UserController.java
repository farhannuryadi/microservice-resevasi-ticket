package com.farhan.userservice.controller;

import com.farhan.userservice.dto.ResponseData;
import com.farhan.userservice.dto.SignupRequest;
import com.farhan.userservice.dto.UserResponse;
import com.farhan.userservice.entity.UserEntity;
import com.farhan.userservice.service.UserService;
import com.farhan.userservice.utility.ErrorParsingUtility;
import com.farhan.userservice.utility.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.farhan.userservice.utility.StatusMsg.ERROR_SERVER;
import static com.farhan.userservice.utility.StatusMsg.SUCCSESS;

@RestController
@RequestMapping("/bioskop/api/users")
//@CrossOrigin(origins = "*", maxAge = 3600)
//@PreAuthorize("hasRole('ADMIN')")
//@Tag(name = "User", description = "Operation about user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
//    @Autowired
//    PasswordEncoder encoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //add user sekarang lewat AuthController
//    @Operation(summary = "Add a new user")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Request Error Message"),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @PostMapping
    public ResponseEntity<ResponseData<UserResponse>> create(@Valid @RequestBody SignupRequest signupRequest, Errors errors){

        ResponseData<UserResponse> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("error request : {}", ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try{
            UserEntity userEntity = new UserEntity(signupRequest.getUsername(),
                signupRequest.getFullName(),
                signupRequest.getAddress(),
                signupRequest.getEmail(),
                signupRequest.getPassword()
//                encoder.encode(signupRequest.getPassword()
                );
            UserEntity user = userService.save(userEntity);
            UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(),
                    user.getFullName(), user.getAddress(), user.getEmail());
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(userResponse);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(true);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Get a user by its username")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @GetMapping("/{username}")
    public ResponseEntity<ResponseData<UserEntity>> findOne(@PathVariable("username") String username){
        ResponseData<UserEntity> responseData = new ResponseData<>();
        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(userService.findOne(username));
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Get all users")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<UserEntity>>> findAll(){
        ResponseData<Iterable<UserEntity>> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(userService.findAll());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Update a user")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
//            }),
//            @ApiResponse(responseCode = "400", description = "Request Error Message"),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
//    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public ResponseEntity<ResponseData<UserResponse>> update(@Valid @RequestBody SignupRequest signupRequest, Errors errors){
        ResponseData<UserResponse> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("error request : {}", ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try{
            UserEntity userEntity = new UserEntity(signupRequest.getUsername(),
                    signupRequest.getFullName(),
                    signupRequest.getAddress(),
                    signupRequest.getEmail(),
                    signupRequest.getPassword()
//                    encoder.encode(signupRequest.getPassword())
            );
            UserEntity user = userService.save(userEntity);
            UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(),
                    user.getFullName(), user.getAddress(), user.getEmail());
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(userResponse);
            logger.info("sukses update user id : {}", userEntity.getId());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(true);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Delete a user by its username")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses"),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
//    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{username}")
    public ResponseEntity<ResponseData<String>> removeOne(@PathVariable("username") String username){
        ResponseData<String> responseData = new ResponseData<>();

        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            userService.removeOne(username);
            logger.info("delete user : {}", username);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @GetMapping("/find/{username}")
    public UserResponse findUser(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }
}
