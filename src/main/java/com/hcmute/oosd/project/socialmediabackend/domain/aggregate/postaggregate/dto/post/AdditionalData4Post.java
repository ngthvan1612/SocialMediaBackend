package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post;

import lombok.Data;

@Data
public class AdditionalData4Post {
    private Integer totalReact;

    private Boolean isReacted;

    private Integer totalComment;

    public AdditionalData4Post(Integer totalReact, Boolean isReacted, Integer totalComment) {
        this.totalReact = totalReact;
        this.isReacted = isReacted;
        this.totalComment = totalComment;
    }
}
