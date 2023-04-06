package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.types.ChatMessageOneToOneType;
import lombok.Data;

import java.util.Date;

@Data
public class ChatMessageOneToOne {
    private Integer senderId;
    private Integer receiverId;
    private String message;
    private ChatMessageOneToOneType type;
    private String randomHash;

    private Date createdAt;

    public ChatMessageOneToOne() {

    }

    public ChatMessageOneToOne(Integer senderId, Integer receiverId, Message message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.type = ChatMessageOneToOneType.MESSAGE;
    }
}
