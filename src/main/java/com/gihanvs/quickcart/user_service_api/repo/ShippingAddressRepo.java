package com.gihanvs.quickcart.user_service_api.repo;

import com.gihanvs.quickcart.user_service_api.Entity.BillingAddress;
import com.gihanvs.quickcart.user_service_api.Entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepo extends JpaRepository<ShippingAddress, String> {

}
