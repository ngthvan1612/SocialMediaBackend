package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetGroupMessageResponse extends SuccessfulResponse {
    public GetGroupMessageResponse(GroupMessageResponse groupMessageResponse) {
        super();
        this.setData(groupMessageResponse);
    }
}
