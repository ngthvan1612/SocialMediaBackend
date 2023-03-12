package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Comment;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for Comment
 */
@Data
public class CommentResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;

    private String content;

    private Integer userId;

    private Integer postId;

    private Integer parentId;


    public CommentResponse(Comment comment) {

        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
        this.lastUpdatedAt = comment.getLastUpdatedAt();
        this.deletedAt = comment.getDeletedAt();
        this.content = comment.getContent();
        if (comment.getUser() != null) {
            this.userId = comment.getUser().getId();
        }

        if (comment.getPost() != null) {
            this.postId = comment.getPost().getId();
        }

        if (comment.getParent() != null) {
            this.parentId = comment.getParent().getId();
        }

    }
}