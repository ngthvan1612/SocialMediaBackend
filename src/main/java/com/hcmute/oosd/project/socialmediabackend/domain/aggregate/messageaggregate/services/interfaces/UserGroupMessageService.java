package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.CreateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.GetUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.ListUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.UpdateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface UserGroupMessageService {
    SuccessResponse createUserGroupMessage(CreateUserGroupMessageRequest request);

    GetUserGroupMessageResponse getUserGroupMessageById(Integer id);

    ListUserGroupMessageResponse searchUserGroupMessages(Map<String, String> queries);

    SuccessResponse updateUserGroupMessage(UpdateUserGroupMessageRequest request);

    SuccessResponse deleteUserGroupMessage(Integer id);


}
