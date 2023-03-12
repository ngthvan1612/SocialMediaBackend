package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateCommentRequest {
    @JsonIgnore
    private Integer commentId;

    private String content;

    private Integer userId;
    private Integer postId;
    private Integer parentId;
}
