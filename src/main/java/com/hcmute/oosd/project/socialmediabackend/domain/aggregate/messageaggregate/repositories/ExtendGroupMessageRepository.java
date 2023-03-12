package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.GroupMessage;

import java.util.List;
import java.util.Map;

public interface ExtendGroupMessageRepository {
    List<GroupMessage> searchGroupMessage(Map<String, String> queries);
}