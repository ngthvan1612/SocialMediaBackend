package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListFollowerResponse extends ResponseBaseAbstract {
    public ListFollowerResponse(List<FollowerResponse> followerResponses) {
        super();
        this.setData(followerResponses);
    }
}
