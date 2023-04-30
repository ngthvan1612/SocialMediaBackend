package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.enums.PostPrivacy;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for Post
 */
@Data
public class PostResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;

    private String content;

    private PostPrivacy privacy;

    private Integer authorId;

    private String username;

    private String avatar;

    private Integer totalReact;

    private Boolean isReacted;

    private Integer totalComment;


    public PostResponse(Post post) {

        this.id = post.getId();
        this.createdAt = post.getCreatedAt();
        this.lastUpdatedAt = post.getLastUpdatedAt();
        this.deletedAt = post.getDeletedAt();
        this.content = post.getContent();
        this.privacy = post.getPrivacy();
        if (post.getAuthor() != null) {
            this.authorId = post.getAuthor().getId();
            this.username = post.getAuthor().getUsername();
            this.avatar = post.getAuthor().getAvatar();
        }
    }
    public PostResponse(Post post, AdditionalData4Post data) {

        this.id = post.getId();
        this.createdAt = post.getCreatedAt();
        this.lastUpdatedAt = post.getLastUpdatedAt();
        this.deletedAt = post.getDeletedAt();
        this.content = post.getContent();
        this.privacy = post.getPrivacy();
        if (post.getAuthor() != null) {
            this.authorId = post.getAuthor().getId();
            this.username = post.getAuthor().getUsername();
            this.avatar = post.getAuthor().getAvatar();
        }
        this.totalReact = data.getTotalReact();
        this.isReacted = data.getIsReacted();
        this.totalComment = data.getTotalComment();
    }
}