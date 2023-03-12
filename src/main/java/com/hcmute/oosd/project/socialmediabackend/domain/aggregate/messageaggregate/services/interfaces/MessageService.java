package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.CreateMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.GetMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.ListMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.UpdateMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface MessageService {
    SuccessfulResponse createMessage(CreateMessageRequest request);

    GetMessageResponse getMessageById(Integer id);

    ListMessageResponse searchMessages(Map<String, String> queries);

    SuccessfulResponse updateMessage(UpdateMessageRequest request);

    SuccessfulResponse deleteMessage(Integer id);


}
