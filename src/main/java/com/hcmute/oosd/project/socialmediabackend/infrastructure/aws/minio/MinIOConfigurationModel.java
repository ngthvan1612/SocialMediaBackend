package com.hcmute.oosd.project.socialmediabackend.infrastructure.aws.minio;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySources({
        @PropertySource(value = "classpath:storage.production.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:storage.development.properties", ignoreResourceNotFound = true)
})
@Data
public class MinIOConfigurationModel {

    @Value("${aws.minio.end-point}")
    private String endPoint;

    @Value("${aws.minio.access-key}")
    private String accessKey;

    @Value("${aws.minio.secret-key}")
    private String secretKey;

    @Value("${aws.minio.default-bucket}")
    private String defaultBucket;

    @Value("${aws.minio.default-image-upload}")
    private String defaultPostIamges;

    @Value("${aws.minio.default-upload-avatar}")
    private String defaultAvatar;
}
