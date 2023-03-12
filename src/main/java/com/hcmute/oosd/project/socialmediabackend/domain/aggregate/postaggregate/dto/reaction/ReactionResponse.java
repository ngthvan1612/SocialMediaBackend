package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Reaction;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.enums.ReactionType;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for Reaction
 */
@Data
public class ReactionResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;

    private ReactionType reaction;

    private Integer userId;

    private Integer postId;


    public ReactionResponse(Reaction reaction) {

        this.id = reaction.getId();
        this.createdAt = reaction.getCreatedAt();
        this.lastUpdatedAt = reaction.getLastUpdatedAt();
        this.deletedAt = reaction.getDeletedAt();
        this.reaction = reaction.getReaction();
        if (reaction.getUser() != null) {
            this.userId = reaction.getUser().getId();
        }

        if (reaction.getPost() != null) {
            this.postId = reaction.getPost().getId();
        }

    }
}