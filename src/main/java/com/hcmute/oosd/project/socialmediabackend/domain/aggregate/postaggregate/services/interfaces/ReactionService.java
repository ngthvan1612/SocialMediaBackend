package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.GetReactionResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.ListReactionResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.UpdateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface ReactionService {
    SuccessResponse createReaction(CreateReactionRequest request);

    GetReactionResponse getReactionById(Integer id);

    ListReactionResponse searchReactions(Map<String, String> queries);

    SuccessResponse updateReaction(UpdateReactionRequest request);

    SuccessResponse deleteReaction(Integer id);


}
