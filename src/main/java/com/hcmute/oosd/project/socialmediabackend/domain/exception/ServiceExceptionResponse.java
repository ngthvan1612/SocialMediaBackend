package com.hcmute.oosd.project.socialmediabackend.domain.exception;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

public class ServiceExceptionResponse extends ResponseBaseAbstract {
    public ServiceExceptionResponse() {
        super();
        this.setStatus("FAIL");
    }
}