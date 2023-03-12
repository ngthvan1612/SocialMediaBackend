package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.GroupMessage;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for GroupMessage
 */
@Data
public class GroupMessageResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;

    private String displayName;

    private Integer adminId;


    public GroupMessageResponse(GroupMessage groupMessage) {

        this.id = groupMessage.getId();
        this.createdAt = groupMessage.getCreatedAt();
        this.lastUpdatedAt = groupMessage.getLastUpdatedAt();
        this.deletedAt = groupMessage.getDeletedAt();
        this.displayName = groupMessage.getDisplayName();
        if (groupMessage.getAdmin() != null) {
            this.adminId = groupMessage.getAdmin().getId();
        }

    }
}