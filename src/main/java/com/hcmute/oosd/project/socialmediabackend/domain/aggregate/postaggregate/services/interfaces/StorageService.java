package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.image.UploadImgRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public interface StorageService {
    SuccessfulResponse uploadImg(UploadImgRequest uploadImgRequest);
}
