package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetReactionResponse extends SuccessResponse {
    public GetReactionResponse(ReactionResponse reactionResponse) {
        super();
        this.setData(reactionResponse);
    }
}
