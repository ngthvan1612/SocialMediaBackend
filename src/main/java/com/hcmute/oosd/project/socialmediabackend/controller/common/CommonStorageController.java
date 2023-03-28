package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.image.UploadImgRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.StorageService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("api/common/storage")
public class CommonStorageController {
    @Autowired
    private StorageService storageService;

    public CommonStorageController(){}

    @PostMapping("/img")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        UploadImgRequest request = new UploadImgRequest();
        request.setUploadFileName(file.getName());
        request.setImgBufferByteArray(file.getBytes());
        SuccessfulResponse createImgUrl = this.storageService.uploadImg(request);
        return createImgUrl;
    }
}
