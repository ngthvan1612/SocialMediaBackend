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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ResponseBaseBuilder<SuccessResponse, Builder> {
        public Builder() {
            super(new SuccessResponse());
        }

        public SuccessResponse returnCreated() {
            this.setStatusCode(HttpStatus.CREATED);
            return this.instance;
        }

        public SuccessResponse returnUpdated() {
            this.setStatusCode(HttpStatus.OK);
            return this.instance;
        }

        public SuccessResponse returnDeleted() {
            this.setStatusCode(HttpStatus.OK);
            return this.instance;
        }

        public SuccessResponse returnGetOK() {
            this.setStatusCode(HttpStatus.OK);
            return this.instance;
        }
    }
}