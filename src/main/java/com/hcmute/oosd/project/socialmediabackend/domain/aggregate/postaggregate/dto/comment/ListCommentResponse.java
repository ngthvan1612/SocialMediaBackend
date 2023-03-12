package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListCommentResponse extends SuccessfulResponse {
    public ListCommentResponse(List<CommentResponse> commentResponses) {
        super();
        this.setData(commentResponses);
    }
}
