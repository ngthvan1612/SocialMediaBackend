package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetUserResponse extends SuccessResponse {
    public GetUserResponse(UserResponse userResponse) {
        super();
        this.setData(userResponse);
    }
}
