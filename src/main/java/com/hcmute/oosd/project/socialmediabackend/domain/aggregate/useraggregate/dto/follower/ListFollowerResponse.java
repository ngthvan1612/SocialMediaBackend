package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListFollowerResponse extends SuccessfulResponse {
    public ListFollowerResponse(List<FollowerResponse> followerResponses) {
        super();
        this.setData(followerResponses);
    }
}
