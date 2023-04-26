package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListPostResponse extends SuccessResponse {
    public ListPostResponse(List<PostResponse> postResponses) {
        super();
        this.setData(postResponses);
    }
}
