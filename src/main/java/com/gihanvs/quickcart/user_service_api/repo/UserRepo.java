package com.gihanvs.quickcart.user_service_api.repo;


import com.gihanvs.quickcart.user_service_api.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, String> {
    public Optional<User> findByUserName(String email);
}
