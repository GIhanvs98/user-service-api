package com.gihanvs.quickcart.user_service_api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table (name="user")

public class User {
    @Id
    @Column(unique = true , nullable = false,name = "user_id")
    private String userId;
    @Column(unique = true , nullable = false,name = "user_name" ,length = 100)
    private String userName;
    @Column(nullable = false,name = "first_name" ,length = 50)
    private String firstName;
    @Column(nullable = false,name = "last_name" ,length = 50)
    private String lastName;
    @Column(nullable = false,name = "active_status",columnDefinition = "TINYINT")
    private boolean activeStatus;
    @Column(nullable = false,name = "OTP")
    private int otp;

}
