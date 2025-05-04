package com.gihanvs.quickcart.user_service_api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
