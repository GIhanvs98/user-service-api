package com.gihanvs.quickcart.user_service_api.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ResponseUserAvatarDto {
    private String hash;
    private String resourceUrl;
    private String directory;
    private String fileName;
}
