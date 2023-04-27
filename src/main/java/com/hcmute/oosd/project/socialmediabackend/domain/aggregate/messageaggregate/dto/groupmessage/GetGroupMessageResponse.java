package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetGroupMessageResponse extends SuccessResponse {
    public GetGroupMessageResponse(GroupMessageResponse groupMessageResponse) {
        super();
        this.setData(groupMessageResponse);
    }
}
