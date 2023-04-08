package com.hcmute.oosd.project.socialmediabackend.domain.base;

import org.springframework.http.HttpStatus;

public class SuccessfulResponse extends ResponseBaseAbstract {
    public SuccessfulResponse() {
        this.setStatusCode(HttpStatus.OK);
    }

    public SuccessfulResponse(HttpStatus statusCode) {
        this.setStatusCode(statusCode);
    }

    @Override
    public void setStatusCode(HttpStatus statusCode) {
        assert statusCode.value() <= 399;
        super.setStatusCode(statusCode);
    }

    public static class Builder extends ResponseBaseBuilder<SuccessfulResponse, Builder> {
        public Builder() {
            super(new SuccessfulResponse());
        }
    }
}