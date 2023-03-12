package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetPostResponse extends SuccessfulResponse {
    public GetPostResponse(PostResponse postResponse) {
        super();
        this.setData(postResponse);
    }
}
