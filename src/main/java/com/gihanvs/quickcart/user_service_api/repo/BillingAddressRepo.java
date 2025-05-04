package com.gihanvs.quickcart.user_service_api.repo;

import com.gihanvs.quickcart.user_service_api.Entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepo extends JpaRepository<BillingAddress, String> {

}
