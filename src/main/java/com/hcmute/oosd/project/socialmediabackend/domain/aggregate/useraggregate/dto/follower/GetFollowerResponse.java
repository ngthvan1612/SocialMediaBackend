package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetFollowerResponse extends SuccessResponse {
    public GetFollowerResponse(FollowerResponse followerResponse) {
        super();
        this.setData(followerResponse);
    }
}
