package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story;

import lombok.Data;

@Data
public class CreateStoryRequest {


    private String content;

    private Integer userId;

}