package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetMessageResponse extends SuccessResponse {
    public GetMessageResponse(MessageResponse messageResponse) {
        super();
        this.setData(messageResponse);
    }
}
