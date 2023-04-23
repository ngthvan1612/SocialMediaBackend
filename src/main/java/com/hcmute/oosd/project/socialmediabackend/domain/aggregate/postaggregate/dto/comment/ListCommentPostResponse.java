package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListCommentPostResponse extends SuccessfulResponse {
    public ListCommentPostResponse(List<CommentPostResponse> commentResponses) {
        super();
        this.setData(commentResponses);
    }
}
