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

    @Deprecated
    public void setStatus(String status) {
        this.status = status;
    }

    @Deprecated
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Deprecated
    public void setData(Object data) {
        this.data = data;
    }

    @Deprecated
    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public ResponseBaseAbstract() {
        this.statusCode = HttpStatus.OK;
        this.status = "OK";
        this.data = null;
        this.messages = new ArrayList<>();
    }

    public ResponseBaseAbstract(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Deprecated
    public void addMessage(String message) {
        this.messages.add(message);
    }

    public static class ResponseBaseBuilder<C extends ResponseBaseAbstract, T extends ResponseBaseBuilder<C, T>> {
        protected final C instance;

        public ResponseBaseBuilder(C instance) {
            this.instance = instance;
        }

        public T setStatusString(String statusString) {
            this.instance.setStatus(statusString);
            return this.getThis();
        }

        public T setStatusCode(HttpStatus httpStatus) {
            this.instance.setStatusCode(httpStatus);
            return this.getThis();
        }

        public T setData(Object data) {
            this.instance.setData(data);
            return this.getThis();
        }

        public T setStatusCode(Integer httpStatus) {
            this.instance.setStatusCode(HttpStatus.valueOf(httpStatus));
            return this.getThis();
        }

        public T addMessage(String message) {
            this.instance.getMessages().add(message);
            return this.getThis();
        }

        @SuppressWarnings(value = "unchecked")
        private T getThis() {
            return (T)this;
        }

        protected ResponseBaseAbstract build() {
            return this.instance;
        }
    }
}
