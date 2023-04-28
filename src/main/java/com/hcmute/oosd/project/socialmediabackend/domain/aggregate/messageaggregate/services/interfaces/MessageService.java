package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToOne;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.ListUserResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import java.util.Map;

public interface MessageService {
    ResponseBaseAbstract getListMessageWithAnotherPerson(GetListMessageWithAnotherPersonRequest request);

    ResponseBaseAbstract storeMessage(ChatMessageOneToOne message);

    ResponseBaseAbstract createMessage(CreateMessageRequest request);

    GetMessageResponse getMessageById(Integer id);

    ListMessageResponse searchMessages(Map<String, String> queries);

    ResponseBaseAbstract updateMessage(UpdateMessageRequest request);

    ResponseBaseAbstract deleteMessage(Integer id);

    ListMessageResponse getMessageFromOneToOne(Integer senderId, Integer receiverId);
    
    ListUserResponse getAllUserHaveBeenChat (Integer userId);

}
