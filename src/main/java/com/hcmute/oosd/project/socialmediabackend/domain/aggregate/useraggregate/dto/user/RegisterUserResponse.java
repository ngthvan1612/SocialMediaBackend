package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class RegisterUserResponse extends SuccessfulResponse {
    public RegisterUserResponse (UserResponse userResponse){
        super();
        this.setData(userResponse);
    }
}
