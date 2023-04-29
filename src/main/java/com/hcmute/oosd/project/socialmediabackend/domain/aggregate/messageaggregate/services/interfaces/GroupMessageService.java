package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.CreateGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.GetGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.ListGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.UpdateGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToGroup;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface GroupMessageService {
    SuccessResponse createGroupMessage(CreateGroupMessageRequest request);

    GetGroupMessageResponse getGroupMessageById(Integer id);

    ListGroupMessageResponse searchGroupMessages(Map<String, String> queries);

    SuccessResponse updateGroupMessage(UpdateGroupMessageRequest request);

//    SuccessfulResponse addUserToGroup(Integer groupId, Integer userId);
    SuccessResponse groupstoreMessage(ChatMessageOneToGroup message);
    SuccessResponse deleteGroupMessage(Integer id);


}
