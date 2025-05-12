package com.gihanvs.quickcart.user_service_api.repo;

import com.gihanvs.quickcart.user_service_api.Entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OtpRepo  extends JpaRepository<Otp, String> {
    @Query(nativeQuery = true,value = "SELECT & FROM otp WHERE user_property_id=1")
    public Optional<Otp> findBySystemUserId(String id);


}
