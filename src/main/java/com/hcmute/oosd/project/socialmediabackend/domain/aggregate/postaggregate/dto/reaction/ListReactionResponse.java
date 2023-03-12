package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListReactionResponse extends SuccessfulResponse {
    public ListReactionResponse(List<ReactionResponse> reactionResponses) {
        super();
        this.setData(reactionResponses);
    }
}
