package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.HashMap;
import java.util.Map;

public class LoginResponse extends SuccessfulResponse {
    public LoginResponse(UserResponse userResponse, String jwtToken) {
        super();

        Map<String, Object> data = new HashMap<>();
        data.put("user", userResponse);
        data.put("accessToken", jwtToken);
        this.setData(data);
    }
}
