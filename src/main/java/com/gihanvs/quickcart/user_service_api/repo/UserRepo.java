package com.gihanvs.quickcart.user_service_api.repo;


import com.gihanvs.quickcart.user_service_api.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    public Optional<User> findByUserName(String email);
}
