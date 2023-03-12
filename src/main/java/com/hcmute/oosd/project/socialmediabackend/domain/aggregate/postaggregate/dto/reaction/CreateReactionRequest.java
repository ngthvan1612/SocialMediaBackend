package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.enums.ReactionType;
import lombok.Data;

@Data
public class CreateReactionRequest {


    private ReactionType reaction;

    private Integer userId;
    private Integer postId;

}