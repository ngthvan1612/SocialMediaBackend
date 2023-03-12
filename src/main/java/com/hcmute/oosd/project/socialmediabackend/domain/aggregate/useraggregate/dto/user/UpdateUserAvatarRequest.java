package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import lombok.Data;

@Data
public class UpdateUserAvatarRequest {
    private Integer userId;
    private byte[] avatarBufferByteArray;
    private String uploadFileName;

    public UpdateUserAvatarRequest() {

    }
}
