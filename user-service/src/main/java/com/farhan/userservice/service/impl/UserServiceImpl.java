package com.farhan.userservice.service.impl;

import com.farhan.userservice.dto.UserResponse;
import com.farhan.userservice.entity.UserEntity;
import com.farhan.userservice.repository.UserRepository;
import com.farhan.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findOne(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void removeOne(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        user.ifPresent(userEntity -> userRepository.deleteById(userEntity.getId()));
    }

    @Override
    public UserResponse findByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return userEntity.map(entity -> new UserResponse(
                entity.getId(),
                entity.getUsername(),
                entity.getFullName(),
                entity.getAddress(),
                entity.getEmail())).orElse(null);
    }
}
