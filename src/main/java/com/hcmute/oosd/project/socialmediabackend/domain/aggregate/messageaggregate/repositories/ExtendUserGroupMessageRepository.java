package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.UserGroupMessage;

import java.util.List;
import java.util.Map;

public interface ExtendUserGroupMessageRepository {
    List<UserGroupMessage> searchUserGroupMessage(Map<String, String> queries);
}