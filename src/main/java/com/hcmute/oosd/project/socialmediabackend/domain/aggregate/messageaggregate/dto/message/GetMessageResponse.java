package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetMessageResponse extends SuccessfulResponse {
    public GetMessageResponse(MessageResponse messageResponse) {
        super();
        this.setData(messageResponse);
    }
}
