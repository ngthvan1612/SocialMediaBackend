package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.types.ChatMessageOneToOneType;
import lombok.Data;

import java.util.Date;

@Data
public class ChatMessageOneToOne {
    private Integer senderId;
    private Integer receivedId;
    private String message;
    private ChatMessageOneToOneType type;
    private String randomHash;

    private Date createdAt;

    public ChatMessageOneToOne() {

    }

    public ChatMessageOneToOne(Integer senderId, Integer receivedId, Message message) {
        this.senderId = senderId;
        this.receivedId = receivedId;
        this.message = message.getContent();
        this.createdAt = message.getCreatedAt();
    }
}
