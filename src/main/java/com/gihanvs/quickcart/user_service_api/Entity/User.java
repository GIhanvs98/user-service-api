package com.gihanvs.quickcart.user_service_api.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Column(name = "is_account_non_expired", columnDefinition = "TINYINT", nullable = false)
    private Boolean isAccountNonExpired;

    @Column(name = "is_email_verified", columnDefinition = "TINYINT", nullable = false)
    private Boolean isEmailVerified;

    @Column(name = "is_account_non_locked", columnDefinition = "TINYINT", nullable = false)
    private Boolean isAccountNonLocked;

    @Column(name = "is_enabled", columnDefinition = "TINYINT", nullable = false)
    private Boolean isEnabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, columnDefinition = "DATETIME")
    private Date createdDate;



    @OneToOne(mappedBy = "user")
    private ShippingAddress shippingAddress;

    @OneToOne(mappedBy = "user")
    private BillingAddress billingAddress;

    @OneToOne(mappedBy = "user")
    private UserAvatar userAvatar;

    @OneToOne(mappedBy = "systemUser",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Otp otp;
}
