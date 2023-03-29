package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
    private String displayName;
    private String email;
}
