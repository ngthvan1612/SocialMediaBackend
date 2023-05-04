package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for Message
 */
@Data
public class MessageResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;
    private Boolean isRead = false;

    private String content;

    private Integer senderId;

    private Integer receiverId;

    private Integer groupId;


    public MessageResponse(Message message) {

        this.id = message.getId();
        this.createdAt = message.getCreatedAt();
        this.lastUpdatedAt = message.getLastUpdatedAt();
        this.deletedAt = message.getDeletedAt();
        this.content = message.getContent();
        this.isRead = message.getIsRead();
        if (message.getSender() != null) {
            this.senderId = message.getSender().getId();
        }

        if (message.getReceiver() != null) {
            this.receiverId = message.getReceiver().getId();
        }

        if (message.getGroup() != null) {
            this.groupId = message.getGroup().getId();
        }

    }
}