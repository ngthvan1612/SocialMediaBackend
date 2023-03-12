package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;

import java.util.List;
import java.util.Map;

public interface ExtendMessageRepository {
    List<Message> searchMessage(Map<String, String> queries);
}