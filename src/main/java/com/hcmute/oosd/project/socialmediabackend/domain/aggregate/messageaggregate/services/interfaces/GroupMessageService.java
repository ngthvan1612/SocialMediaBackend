package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.CreateGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.GetGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.ListGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.UpdateGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToGroup;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;
import java.util.Map;

public interface GroupMessageService {
    ResponseBaseAbstract createGroupMessage(CreateGroupMessageRequest request);


    ResponseBaseAbstract getGroupMessageById(Integer id);

    ResponseBaseAbstract searchGroupMessages(Map<String, String> queries);

    ResponseBaseAbstract updateGroupMessage(UpdateGroupMessageRequest request);


    ResponseBaseAbstract CreateGroup(String groupName, List<Integer> memberIds);

    ResponseBaseAbstract AddUserToGroup(Integer groupId, List<Integer> memberIds);
    SuccessResponse groupstoreMessage(ChatMessageOneToGroup message);
    ResponseBaseAbstract deleteGroupMessage(Integer id);



}
