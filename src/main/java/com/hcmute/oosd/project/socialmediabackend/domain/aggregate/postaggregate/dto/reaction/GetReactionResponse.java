package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetReactionResponse extends SuccessfulResponse {
    public GetReactionResponse(ReactionResponse reactionResponse) {
        super();
        this.setData(reactionResponse);
    }
}
