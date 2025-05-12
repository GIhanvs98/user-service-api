package com.gihanvs.quickcart.user_service_api.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_avatar")
@Builder
public class UserAvatar {
    @Id
    @Column(nullable = false,unique = true,name = "avatar_id")
    private String avatarId;

    @Embedded
    private FileResource fileResource;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;


}
