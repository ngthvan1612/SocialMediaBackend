package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class RegisterUserResponse extends ResponseBaseAbstract {
    public RegisterUserResponse(UserResponse userResponse) {
        super();
        this.setData(userResponse);
    }
}
