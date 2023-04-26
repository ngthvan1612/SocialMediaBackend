package com.hcmute.oosd.project.socialmediabackend.domain.base;

import org.springframework.http.HttpStatus;

public class SuccessResponse extends ResponseBaseAbstract {
    public SuccessResponse() {
        this.setStatusCode(HttpStatus.OK);
    }

    public SuccessResponse(HttpStatus statusCode) {
        this.setStatusCode(statusCode);
    }

    @Override
    public void setStatusCode(HttpStatus statusCode) {
        assert statusCode.value() <= 399;
        super.setStatusCode(statusCode);
    }

    public static class Builder extends ResponseBaseBuilder<SuccessResponse, Builder> {
        public Builder() {
            super(new SuccessResponse());
        }
    }
}