package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
