package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.enums.PostPrivacy;
import lombok.Data;

@Data
public class CreatePostRequest {


    private String content;


    private PostPrivacy privacy;

    private Integer authorId;

}