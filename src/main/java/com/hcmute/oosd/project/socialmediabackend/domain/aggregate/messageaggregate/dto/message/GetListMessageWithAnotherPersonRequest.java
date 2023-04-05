package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class GetListMessageWithAnotherPersonRequest {
    @JsonIgnore
    private Integer userId;
    private Integer friendId;
}
