package com.farhan.userservice.utility;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message){
        super(message);
    }
}
