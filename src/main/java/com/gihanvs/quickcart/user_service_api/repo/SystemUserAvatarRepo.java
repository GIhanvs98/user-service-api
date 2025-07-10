package com.gihanvs.quickcart.user_service_api.repo;


import com.gihanvs.quickcart.user_service_api.Entity.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface SystemUserAvatarRepo extends JpaRepository<UserAvatar,String> {

    @Query(value = "SELECT * FROM system_user_avatar WHERE user_property_id=?1", nativeQuery = true)
    public Optional<UserAvatar> findByUserId(String id);

}
