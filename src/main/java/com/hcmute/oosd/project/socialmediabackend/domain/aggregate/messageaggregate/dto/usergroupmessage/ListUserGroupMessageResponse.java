package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListUserGroupMessageResponse extends SuccessResponse {
    public ListUserGroupMessageResponse(List<UserGroupMessageResponse> userGroupMessageResponses) {
        super();
        this.setData(userGroupMessageResponses);
    }
}
