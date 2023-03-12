package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateAnnounceRequest {
    @JsonIgnore
    private Integer announceId;

    private String content;
    private Boolean seen;

    private Integer userId;
}
