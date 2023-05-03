package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.types.ChatMessageOneToOneType;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.json.JSONObject;

import java.net.URL;
import java.util.Date;

@Data

@AllArgsConstructor
public class ChatMessageOneToOne {
    private Integer senderId;
    private Integer receiverId;
    private String message;

    private Integer messageId;
    private ChatMessageOneToOneType type;
    private String randomHash;
    private boolean isRead;
    private Date createdAt;
    private String image;

    public ChatMessageOneToOne() {

    }

    public ChatMessageOneToOne(Integer senderId, Integer receiverId, Message message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageId = message.getId();
        this.message = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.isRead = message.getIsRead();
        if (message.getContent().startsWith("img:")) {
            this.type = ChatMessageOneToOneType.IMAGE;
            this.image = message.getContent().replaceFirst("img:", "");
        } else {
            this.type = ChatMessageOneToOneType.MESSAGE;
        }
    }
    public void setMessageAsRead() {
        this.isRead = true;
    }
}
