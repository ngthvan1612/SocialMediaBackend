package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage;

import lombok.Data;

@Data
public class CreateUserGroupMessageRequest {

    private Integer groupId;
    private Integer memberId;

}