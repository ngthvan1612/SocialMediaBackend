package com.hcmute.oosd.project.socialmediabackend.domain.base;

import java.io.InputStream;

public interface StorageRepository {
    String saveUploadedStream(String uploadFileName, InputStream inputStream, long length);
}