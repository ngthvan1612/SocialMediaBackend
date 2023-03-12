package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.UserTagFriendPost;

import java.util.List;
import java.util.Map;

public interface ExtendUserTagFriendPostRepository {
    List<UserTagFriendPost> searchUserTagFriendPost(Map<String, String> queries);
}