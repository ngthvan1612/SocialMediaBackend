package com.hcmute.oosd.project.socialmediabackend.domain.base;

import java.io.InputStream;

public interface StorageRepository {
    String savedUploadedAvatar(String uploadFileName, InputStream inputStream, long length);
    String saveUploadedImg(String uploadFileName, InputStream inputStream, long length);
}