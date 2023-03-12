package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetCommentResponse extends SuccessfulResponse {
    public GetCommentResponse(CommentResponse commentResponse) {
        super();
        this.setData(commentResponse);
    }
}
