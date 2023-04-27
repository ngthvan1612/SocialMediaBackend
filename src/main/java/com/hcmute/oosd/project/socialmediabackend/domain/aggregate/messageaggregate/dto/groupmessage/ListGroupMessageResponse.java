package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListGroupMessageResponse extends SuccessResponse {
    public ListGroupMessageResponse(List<GroupMessageResponse> groupMessageResponses) {
        super();
        this.setData(groupMessageResponses);
    }
}
