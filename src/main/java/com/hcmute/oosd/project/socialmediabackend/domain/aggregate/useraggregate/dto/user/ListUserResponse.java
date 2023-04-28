package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListUserResponse extends SuccessResponse {
    public ListUserResponse(List<UserResponse> userResponses) {
        super();
        this.setData(userResponses);
    }
}
