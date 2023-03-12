package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage;

import lombok.Data;

@Data
public class CreateGroupMessageRequest {


    private String displayName;

    private Integer adminId;

}