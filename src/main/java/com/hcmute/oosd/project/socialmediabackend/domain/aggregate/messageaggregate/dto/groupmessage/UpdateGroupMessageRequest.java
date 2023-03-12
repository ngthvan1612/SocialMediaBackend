package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateGroupMessageRequest {
    @JsonIgnore
    private Integer groupMessageId;

    private String displayName;

    private Integer adminId;
}
