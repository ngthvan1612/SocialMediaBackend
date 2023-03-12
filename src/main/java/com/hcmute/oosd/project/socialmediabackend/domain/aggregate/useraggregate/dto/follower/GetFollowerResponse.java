package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetFollowerResponse extends SuccessfulResponse {
    public GetFollowerResponse(FollowerResponse followerResponse) {
        super();
        this.setData(followerResponse);
    }
}
