package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListUserGroupMessageResponse extends SuccessfulResponse {
    public ListUserGroupMessageResponse(List<UserGroupMessageResponse> userGroupMessageResponses) {
        super();
        this.setData(userGroupMessageResponses);
    }
}
