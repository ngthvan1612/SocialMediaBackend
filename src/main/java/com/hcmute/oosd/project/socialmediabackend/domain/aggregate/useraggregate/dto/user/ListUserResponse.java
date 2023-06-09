package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListUserResponse extends ResponseBaseAbstract {
    public ListUserResponse(List<UserResponse> userResponses) {
        super();
        this.setData(userResponses);
    }
}
