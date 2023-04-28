package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

public class GetUserGroupMessageResponse extends ResponseBaseAbstract {
    public GetUserGroupMessageResponse(UserGroupMessageResponse userGroupMessageResponse) {
        super();
        this.setData(userGroupMessageResponse);
    }
}
