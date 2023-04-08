package com.hcmute.oosd.project.socialmediabackend.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Data
@PropertySources({
        @PropertySource(value = "classpath:jwt.production.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:jwt.development.properties", ignoreResourceNotFound = true),
})
public class JwtConfigurationModel {
    @Value("${jwt.configuration.secret}")
    private String secret;

    @Value("${jwt.configuration.issuer}")
    private String issuer;

    @Value("${jwt.configuration.audience}")
    private String audience;

    @Value("${jwt.configuration.expired}")
    private Long expired;
}