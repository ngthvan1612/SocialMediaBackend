package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.CreateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.GetUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.ListUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.UpdateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import java.util.Map;

public interface UserGroupMessageService {
    ResponseBaseAbstract createUserGroupMessage(CreateUserGroupMessageRequest request);

    ResponseBaseAbstract getUserGroupMessageById(Integer id);

    ResponseBaseAbstract searchUserGroupMessages(Map<String, String> queries);

    ResponseBaseAbstract updateUserGroupMessage(UpdateUserGroupMessageRequest request);

    ResponseBaseAbstract deleteUserGroupMessage(Integer id);

}
