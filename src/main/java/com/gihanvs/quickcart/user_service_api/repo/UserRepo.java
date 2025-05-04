package com.gihanvs.quickcart.user_service_api.repo;


import com.gihanvs.quickcart.user_service_api.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

}
