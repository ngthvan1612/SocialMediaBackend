package com.hcmute.oosd.project.socialmediabackend.domain.base;

import org.springframework.http.HttpStatus;


public class ErrorResponseBase extends ResponseBaseAbstract {
    public ErrorResponseBase() {
        this.setStatus("FAIL");
        this.setStatusCode(HttpStatus.BAD_REQUEST);
    }

    public ErrorResponseBase(HttpStatus statusCode) {
        this.setStatusCode(statusCode);
        this.setStatus("FAIL");
    }

    @Override
    public void setStatusCode(HttpStatus statusCode) {
        assert statusCode.value() >= 400;
        super.setStatusCode(statusCode);
    }

    public static class Builder extends ResponseBaseBuilder<ErrorResponseBase, Builder> {
        public Builder() {
            super(new ErrorResponseBase());
        }
    }
}