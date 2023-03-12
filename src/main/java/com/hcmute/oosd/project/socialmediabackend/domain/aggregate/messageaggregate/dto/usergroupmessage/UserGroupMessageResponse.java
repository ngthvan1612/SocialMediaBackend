package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.UserGroupMessage;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for UserGroupMessage
 */
@Data
public class UserGroupMessageResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;

    private Integer groupId;

    private Integer memberId;


    public UserGroupMessageResponse(UserGroupMessage userGroupMessage) {

        this.id = userGroupMessage.getId();
        this.createdAt = userGroupMessage.getCreatedAt();
        this.lastUpdatedAt = userGroupMessage.getLastUpdatedAt();
        this.deletedAt = userGroupMessage.getDeletedAt();
        if (userGroupMessage.getGroup() != null) {
            this.groupId = userGroupMessage.getGroup().getId();
        }

        if (userGroupMessage.getMember() != null) {
            this.memberId = userGroupMessage.getMember().getId();
        }

    }
}