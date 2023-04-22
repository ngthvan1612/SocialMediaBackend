package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.enums.PostPrivacy;
import lombok.Data;

import java.util.List;

@Data
public class UpdatePostRequest {
    @JsonIgnore
    private Integer postId;

    private String content;
    private PostPrivacy privacy;
    private List<Integer> tags;
    private Integer authorId;
}
