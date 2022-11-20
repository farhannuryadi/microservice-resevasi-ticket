package com.farhan.orderservice.controller;

import com.farhan.orderservice.dto.OrderRequest;
import com.farhan.orderservice.dto.ResponseData;
import com.farhan.orderservice.dto.SeatAvailabelResponse;
import com.farhan.orderservice.service.OrderDetailService;
import com.farhan.orderservice.service.OrderService;
import com.farhan.orderservice.utility.StatusCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.farhan.orderservice.utility.StatusMsg.ERROR_SERVER;
import static com.farhan.orderservice.utility.StatusMsg.SUCCSESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bioskop/api/orders")
//@CrossOrigin(origins = "*", maxAge = 3600)
//@PreAuthorize("hasRole('USER') or hasRole('USER')")
//@Tag(name = "Order", description = "Everything about order")
public class OrderController {

    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

//    @Operation(summary = "Get all seat is available")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = SeatAvailabelResponse.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @GetMapping("/seat/available/{scheduleId}")
    public ResponseEntity<ResponseData<SeatAvailabelResponse>> seatAvailable(@PathVariable("scheduleId") Long scheduleId){
        ResponseData<SeatAvailabelResponse> responseData= new ResponseData<>();
        try {
            responseData.setData(orderDetailService.seatAvilable(scheduleId));
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.setMessages(List.of(SUCCSESS));
            logger.info("call seat available from schedule : {}", scheduleId);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

//    @Operation(summary = "Add a new order by username and scheduleId")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "sukses", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderEntity.class))
//            }),
//            @ApiResponse(responseCode = "500", description = "Server Error Message")
//    })
    @PostMapping("/create/{username}/{scheduleId}")
    public ResponseEntity<ResponseData<String>> createOrder(@PathVariable("username") String username,
                                                    @PathVariable("scheduleId") Long scheduleId,
                                                    @RequestBody OrderRequest orderRequest){

        ResponseData<String> responseData = new ResponseData<>();
        List<String> seats = new ArrayList<>(orderRequest.getSeatName());

        try {
            orderService.createOrder(scheduleId, username, seats);
            orderDetailService.createOrderDetail(seats, scheduleId, username);

            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            logger.info("user : {}, create order from schedule : {}, for seat : {}", username, scheduleId, seats);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }
}
