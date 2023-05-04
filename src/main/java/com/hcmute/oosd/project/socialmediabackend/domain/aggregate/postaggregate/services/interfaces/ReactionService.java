package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.GetReactionResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.ListReactionResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.UpdateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface ReactionService {
    ResponseBaseAbstract createReaction(CreateReactionRequest request);

    ResponseBaseAbstract getReactionById(Integer id);

    ResponseBaseAbstract searchReactions(Map<String, String> queries);

    ResponseBaseAbstract updateReaction(UpdateReactionRequest request);

    ResponseBaseAbstract deleteReaction(Integer id);


}
