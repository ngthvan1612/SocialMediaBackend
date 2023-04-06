package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToOne;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.ListUserResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface MessageService {
    SuccessfulResponse getListMessageWithAnotherPerson(GetListMessageWithAnotherPersonRequest request);

    SuccessfulResponse storeMessage(ChatMessageOneToOne message);

    SuccessfulResponse createMessage(CreateMessageRequest request);

    GetMessageResponse getMessageById(Integer id);

    ListMessageResponse searchMessages(Map<String, String> queries);

    SuccessfulResponse updateMessage(UpdateMessageRequest request);

    SuccessfulResponse deleteMessage(Integer id);
<<<<<<< HEAD
    ListMessageResponse getMessageFromOneToOne(Integer senderId, Integer receiverId);
    ListUserResponse getAllUserHaveBeenChat (Integer userId);
=======
>>>>>>> a31597e6e97b10252f6061480825723c0646032e


}
