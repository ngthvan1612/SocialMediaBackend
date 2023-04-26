package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListMessageResponse extends SuccessResponse {
    public ListMessageResponse(List<MessageResponse> messageResponses) {
        super();
        this.setData(messageResponses);
    }
}
