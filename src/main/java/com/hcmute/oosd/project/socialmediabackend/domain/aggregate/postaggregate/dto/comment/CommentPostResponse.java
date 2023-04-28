package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment;


import lombok.Data;

import java.util.Date;

@Data
public class CommentPostResponse {
    private Integer id;

    private String content;

    private Number numberOfChildren;


    public CommentPostResponse(Integer id, String content, Number numberOfChildren) {
        this.id = id;
        this.content = content;
        this.numberOfChildren = numberOfChildren;
    }
}
