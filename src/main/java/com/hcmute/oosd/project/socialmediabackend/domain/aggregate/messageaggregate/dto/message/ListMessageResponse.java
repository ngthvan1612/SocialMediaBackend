package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListMessageResponse extends SuccessfulResponse {
    public ListMessageResponse(List<MessageResponse> messageResponses) {
        super();
        this.setData(messageResponses);
    }
}
