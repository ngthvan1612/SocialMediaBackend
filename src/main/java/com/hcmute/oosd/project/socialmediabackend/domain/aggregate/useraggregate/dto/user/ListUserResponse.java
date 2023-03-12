package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListUserResponse extends SuccessfulResponse {
    public ListUserResponse(List<UserResponse> userResponses) {
        super();
        this.setData(userResponses);
    }
}
