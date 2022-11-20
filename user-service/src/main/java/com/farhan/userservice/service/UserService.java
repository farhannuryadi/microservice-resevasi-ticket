package com.farhan.userservice.service;


import com.farhan.userservice.dto.UserResponse;
import com.farhan.userservice.entity.UserEntity;

public interface UserService {

    UserEntity save(UserEntity userEntity);
    UserEntity findOne(String username);
    Iterable<UserEntity> findAll();
    void removeOne(String username);
    UserResponse findByUsername(String username);
}
