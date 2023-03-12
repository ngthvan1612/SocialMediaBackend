package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetUserGroupMessageResponse extends SuccessfulResponse {
    public GetUserGroupMessageResponse(UserGroupMessageResponse userGroupMessageResponse) {
        super();
        this.setData(userGroupMessageResponse);
    }
}
