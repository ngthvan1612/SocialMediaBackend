package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetPostResponse extends SuccessResponse {
    public GetPostResponse(PostResponse postResponse) {
        super();
        this.setData(postResponse);
    }
}
