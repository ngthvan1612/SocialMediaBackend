package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateStoryRequest {
    @JsonIgnore
    private Integer storyId;

    private String content;

    private Integer userId;
}
