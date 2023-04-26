package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListFollowerResponse extends SuccessResponse {
    public ListFollowerResponse(List<FollowerResponse> followerResponses) {
        super();
        this.setData(followerResponses);
    }
}
