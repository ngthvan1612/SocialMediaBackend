package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import java.util.List;

public class ListUserGroupMessageResponse extends ResponseBaseAbstract {
    public ListUserGroupMessageResponse(List<UserGroupMessageResponse> userGroupMessageResponses) {
        super();
        this.setData(userGroupMessageResponses);
    }
}
