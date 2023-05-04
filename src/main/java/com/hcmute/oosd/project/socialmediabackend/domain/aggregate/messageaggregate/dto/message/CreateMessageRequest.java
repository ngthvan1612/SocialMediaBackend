package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message;

import lombok.Data;

@Data
public class CreateMessageRequest {


    private String content;
    private Integer senderId;
    private Integer receiverId;
    private Integer groupId;
    private Boolean isRead = false;

}