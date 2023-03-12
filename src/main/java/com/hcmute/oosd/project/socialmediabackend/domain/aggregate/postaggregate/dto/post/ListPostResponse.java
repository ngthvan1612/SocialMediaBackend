package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListPostResponse extends SuccessfulResponse {
    public ListPostResponse(List<PostResponse> postResponses) {
        super();
        this.setData(postResponses);
    }
}
