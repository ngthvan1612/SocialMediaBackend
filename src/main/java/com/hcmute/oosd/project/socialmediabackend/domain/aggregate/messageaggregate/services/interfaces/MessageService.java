package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToOne;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.ListUserResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface MessageService {
    SuccessResponse getListMessageWithAnotherPerson(GetListMessageWithAnotherPersonRequest request);

    SuccessResponse storeMessage(ChatMessageOneToOne message);

    SuccessResponse createMessage(CreateMessageRequest request);

    GetMessageResponse getMessageById(Integer id);

    ListMessageResponse searchMessages(Map<String, String> queries);

    SuccessResponse updateMessage(UpdateMessageRequest request);

    SuccessResponse deleteMessage(Integer id);

    ListMessageResponse getMessageFromOneToOne(Integer senderId, Integer receiverId);
    
    ListUserResponse getAllUserHaveBeenChat (Integer userId);

}
