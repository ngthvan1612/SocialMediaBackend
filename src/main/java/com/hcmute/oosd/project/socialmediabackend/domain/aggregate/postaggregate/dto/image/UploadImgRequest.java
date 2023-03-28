package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.image;

import lombok.Data;

@Data
public class UploadImgRequest {
    private byte[] imgBufferByteArray;
    private String uploadFileName;
}
