package com.farhan.userservice.repository;

import com.farhan.userservice.entity.ERole;
import com.farhan.userservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ERole name);
}
