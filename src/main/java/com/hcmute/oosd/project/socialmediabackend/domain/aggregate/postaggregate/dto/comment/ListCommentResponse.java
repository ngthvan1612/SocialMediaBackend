package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListCommentResponse extends SuccessResponse {
    public ListCommentResponse(List<CommentResponse> commentResponses) {
        super();
        this.setData(commentResponses);
    }
}
