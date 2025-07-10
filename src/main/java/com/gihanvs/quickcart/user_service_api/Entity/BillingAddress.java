package com.gihanvs.quickcart.user_service_api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "billing_address")
public class BillingAddress {
    @Id
    @Column(nullable = false, unique = true)
    private String id;
    @Column(nullable = false,length = 50)
    private String country;
    @Column(nullable = false,length = 50)
    private String city;
    @Column(nullable = false,length = 50)
    private String street;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,unique = true)
    private User user;
}
