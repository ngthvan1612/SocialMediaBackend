package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Story;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for Story
 */
@Data
public class StoryResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;

    private String content;

    private Integer userId;


    public StoryResponse(Story story) {

        this.id = story.getId();
        this.createdAt = story.getCreatedAt();
        this.lastUpdatedAt = story.getLastUpdatedAt();
        this.deletedAt = story.getDeletedAt();
        this.content = story.getContent();
        if (story.getUser() != null) {
            this.userId = story.getUser().getId();
        }

    }
}