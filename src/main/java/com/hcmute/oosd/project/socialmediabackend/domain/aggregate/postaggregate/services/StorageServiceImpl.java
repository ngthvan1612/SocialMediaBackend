package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.image.UploadImgRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.StorageService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.UserServiceImpl;
import com.hcmute.oosd.project.socialmediabackend.domain.base.StorageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.exception.ServiceExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class StorageServiceImpl implements StorageService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private StorageRepository storageRepository;
    @Override
    public SuccessResponse uploadImg(UploadImgRequest request) {
        //Save to MinIO
        InputStream preparedStream = new ByteArrayInputStream(request.getImgBufferByteArray());
        String newMinIOUrl = this.storageRepository.saveUploadedImg(
                request.getUploadFileName(),
                preparedStream,
                request.getImgBufferByteArray().length
        );

        if (newMinIOUrl == null) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Tải lên lỗi");
        }

        //Return
        SuccessResponse response = new SuccessResponse(HttpStatus.CREATED);

        response.setData(newMinIOUrl);
        response.addMessage("Tải hình ảnh lên server thành công");

        LOG.info("New img uploaded successfully in " + newMinIOUrl);
        return response;
    }
}
