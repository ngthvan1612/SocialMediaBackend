package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.CreateGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.GetGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.ListGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.UpdateGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToGroup;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface GroupMessageService {
    SuccessfulResponse createGroupMessage(CreateGroupMessageRequest request);

    GetGroupMessageResponse getGroupMessageById(Integer id);

    ListGroupMessageResponse searchGroupMessages(Map<String, String> queries);

    SuccessfulResponse updateGroupMessage(UpdateGroupMessageRequest request);

//    SuccessfulResponse addUserToGroup(Integer groupId, Integer userId);
    SuccessfulResponse groupstoreMessage(ChatMessageOneToGroup message);
    SuccessfulResponse deleteGroupMessage(Integer id);


}
