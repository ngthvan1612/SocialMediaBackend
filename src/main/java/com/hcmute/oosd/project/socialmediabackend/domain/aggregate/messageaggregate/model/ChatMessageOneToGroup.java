package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.types.ChatMessageOneToGroupType;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.types.ChatMessageOneToOneType;
import lombok.Data;

import java.util.Date;
@Data
public class ChatMessageOneToGroup {
    private Integer memberId;
    private Integer groupId;
    private String message;
    private ChatMessageOneToGroupType type;
    private String randomHash;

    private Date createdAt;

    public ChatMessageOneToGroup() {

    }

    public ChatMessageOneToGroup(Integer senderId, Integer receiverId, Message message) {
        this.memberId = senderId;
        this.groupId = receiverId;
        this.message = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.type = ChatMessageOneToGroupType.MESSAGE;
    }
}
