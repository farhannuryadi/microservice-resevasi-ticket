package com.farhan.userservice;

import com.farhan.userservice.entity.ERole;
import com.farhan.userservice.entity.RoleEntity;
import com.farhan.userservice.entity.UserEntity;
import com.farhan.userservice.repository.RoleRepository;
import com.farhan.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            RoleEntity roleEntity1 = new RoleEntity(ERole.ROLE_ADMIN);
            RoleEntity roleEntity2 = new RoleEntity(ERole.ROLE_USER);

            UserEntity userEntity1 = new UserEntity("farhanryd6", "Farhan Nuryadi", "Depok", "farhan@gmail.com", "123456");
            userEntity1.setRoles(Set.of(roleEntity1, roleEntity2));
            UserEntity userEntity2 = new UserEntity("tirtakrmh99", "Tirta Karimah", "Jakarta Timur", "tirta@gmail.com", "123456");
            userEntity2.setRoles(Set.of(roleEntity2));

            roleRepository.save(roleEntity1);
            roleRepository.save(roleEntity2);
            userRepository.save(userEntity1);
            userRepository.save(userEntity2);
        };
    }
}
