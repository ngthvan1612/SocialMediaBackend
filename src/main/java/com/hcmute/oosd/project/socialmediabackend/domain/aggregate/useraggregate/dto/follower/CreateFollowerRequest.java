package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower;

import lombok.Data;

@Data
public class CreateFollowerRequest {

    private Integer userId;
    private Integer followId;

}