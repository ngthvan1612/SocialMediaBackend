package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.image.UploadImgRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.StorageService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/common/storage")
public class CommonStorageController {
    @Autowired
    private StorageService storageService;

    public CommonStorageController(){}

    @PostMapping("/img")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract uploadImage(@RequestBody UploadImgRequest uploadImgRequest){
        SuccessfulResponse createImgUrl = this.storageService.uploadImg(uploadImgRequest);
        return createImgUrl;
    }
}
