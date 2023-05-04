package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import java.util.List;

public class ListGroupMessageResponse extends ResponseBaseAbstract {
    public ListGroupMessageResponse(List<GroupMessageResponse> groupMessageResponses) {
        super();
        this.setData(groupMessageResponses);
    }
}
