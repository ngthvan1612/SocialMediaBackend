package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

public class GetGroupMessageResponse extends ResponseBaseAbstract {
    public GetGroupMessageResponse(GroupMessageResponse groupMessageResponse) {
        super();
        this.setData(groupMessageResponse);
    }
}
