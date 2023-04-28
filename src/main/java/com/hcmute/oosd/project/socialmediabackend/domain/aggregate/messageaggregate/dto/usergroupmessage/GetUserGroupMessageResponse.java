package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetUserGroupMessageResponse extends SuccessResponse {
    public GetUserGroupMessageResponse(UserGroupMessageResponse userGroupMessageResponse) {
        super();
        this.setData(userGroupMessageResponse);
    }
}
