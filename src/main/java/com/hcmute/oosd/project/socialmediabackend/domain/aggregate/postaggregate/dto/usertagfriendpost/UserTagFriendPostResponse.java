package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.UserTagFriendPost;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for UserTagFriendPost
 */
@Data
public class UserTagFriendPostResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;

    private Integer postId;

    private Integer friendId;


    public UserTagFriendPostResponse(UserTagFriendPost userTagFriendPost) {

        this.id = userTagFriendPost.getId();
        this.createdAt = userTagFriendPost.getCreatedAt();
        this.lastUpdatedAt = userTagFriendPost.getLastUpdatedAt();
        this.deletedAt = userTagFriendPost.getDeletedAt();
        if (userTagFriendPost.getPost() != null) {
            this.postId = userTagFriendPost.getPost().getId();
        }

        if (userTagFriendPost.getFriend() != null) {
            this.friendId = userTagFriendPost.getFriend().getId();
        }

    }
}