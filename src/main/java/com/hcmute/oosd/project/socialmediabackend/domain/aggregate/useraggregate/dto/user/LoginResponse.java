package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.HashMap;
import java.util.Map;

public class LoginResponse extends SuccessResponse {
    public LoginResponse(UserResponse userResponse, String jwtToken) {
        super();

        Map<String, Object> data = new HashMap<>();
        data.put("user", userResponse);
        data.put("accessToken", jwtToken);
        this.setData(data);
    }
}
