package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.enums.ReactionType;
import lombok.Data;

@Data
public class UpdateReactionRequest {
    @JsonIgnore
    private Integer reactionId;

    private ReactionType reaction;

    private Integer userId;
    private Integer postId;
}
