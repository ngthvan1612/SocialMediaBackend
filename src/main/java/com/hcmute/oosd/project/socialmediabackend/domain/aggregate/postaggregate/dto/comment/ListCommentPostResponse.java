package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListCommentPostResponse extends SuccessResponse {
    public ListCommentPostResponse(List<CommentPostResponse> commentResponses) {
        super();
        this.setData(commentResponses);
    }
}
