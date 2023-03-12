package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.entities.Announce;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for Announce
 */
@Data
public class AnnounceResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;

    private String content;

    private Boolean seen;

    private Integer userId;


    public AnnounceResponse(Announce announce) {

        this.id = announce.getId();
        this.createdAt = announce.getCreatedAt();
        this.lastUpdatedAt = announce.getLastUpdatedAt();
        this.deletedAt = announce.getDeletedAt();
        this.content = announce.getContent();
        this.seen = announce.getSeen();
        if (announce.getUser() != null) {
            this.userId = announce.getUser().getId();
        }

    }
}