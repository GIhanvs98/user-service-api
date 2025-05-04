package com.gihanvs.quickcart.user_service_api.dto.response;

import com.gihanvs.quickcart.user_service_api.dto.request.RequestShippingAddressDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ResponseUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private Boolean activeStaus;
    private ResponseUserAvatarDto avatar;
    private RequestShippingAddressDto shippingAddress;
    private ResponseBillingAddressDto billingAddress;
}
