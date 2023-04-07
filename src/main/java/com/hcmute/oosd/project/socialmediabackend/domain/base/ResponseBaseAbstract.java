package com.hcmute.oosd.project.socialmediabackend.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Data
public abstract class ResponseBaseAbstract {
    @JsonIgnore
    private HttpStatus statusCode;

    private String status;
    private Object data;
    private ArrayList<String> messages;

    public ResponseBaseAbstract() {
        this.statusCode = HttpStatus.OK;
        this.status = "OK";
        this.data = null;
        this.messages = new ArrayList<>();
    }

    public ResponseBaseAbstract(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
