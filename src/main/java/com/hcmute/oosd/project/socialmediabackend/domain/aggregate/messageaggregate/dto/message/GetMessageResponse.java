package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

public class GetMessageResponse extends ResponseBaseAbstract {
    public GetMessageResponse(MessageResponse messageResponse) {
        super();
        this.setData(messageResponse);
    }
}
