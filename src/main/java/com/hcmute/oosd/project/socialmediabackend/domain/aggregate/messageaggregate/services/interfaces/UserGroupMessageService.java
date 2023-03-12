package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.CreateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.GetUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.ListUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.UpdateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface UserGroupMessageService {
    SuccessfulResponse createUserGroupMessage(CreateUserGroupMessageRequest request);

    GetUserGroupMessageResponse getUserGroupMessageById(Integer id);

    ListUserGroupMessageResponse searchUserGroupMessages(Map<String, String> queries);

    SuccessfulResponse updateUserGroupMessage(UpdateUserGroupMessageRequest request);

    SuccessfulResponse deleteUserGroupMessage(Integer id);


}
