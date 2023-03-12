package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetUserResponse extends SuccessfulResponse {
    public GetUserResponse(UserResponse userResponse) {
        super();
        this.setData(userResponse);
    }
}
