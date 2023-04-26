package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetCommentResponse extends SuccessResponse {
    public GetCommentResponse(CommentResponse commentResponse) {
        super();
        this.setData(commentResponse);
    }
}
