package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.enums.PostPrivacy;
import lombok.Data;

import java.util.List;

@Data
public class ToggleFollowerRequest {
        @JsonIgnore
        private Integer userId;
        private Integer followerId;
}
