package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListGroupMessageResponse extends SuccessfulResponse {
    public ListGroupMessageResponse(List<GroupMessageResponse> groupMessageResponses) {
        super();
        this.setData(groupMessageResponses);
    }
}
