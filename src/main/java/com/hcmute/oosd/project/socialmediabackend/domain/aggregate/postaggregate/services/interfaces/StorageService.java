package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.image.UploadImgRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public interface StorageService {
    SuccessResponse uploadImg(UploadImgRequest uploadImgRequest);
}
