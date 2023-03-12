package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateFollowerRequest {
    @JsonIgnore
    private Integer followerId;


    private Integer userId;
    private Integer followId;
}
