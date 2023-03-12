package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateUserTagFriendPostRequest {
    @JsonIgnore
    private Integer userTagFriendPostId;


    private Integer postId;
    private Integer friendId;
}
