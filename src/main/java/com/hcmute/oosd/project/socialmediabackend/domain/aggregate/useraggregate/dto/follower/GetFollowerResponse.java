package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

public class GetFollowerResponse extends ResponseBaseAbstract {
    public GetFollowerResponse(FollowerResponse followerResponse) {
        super();
        this.setData(followerResponse);
    }
}
