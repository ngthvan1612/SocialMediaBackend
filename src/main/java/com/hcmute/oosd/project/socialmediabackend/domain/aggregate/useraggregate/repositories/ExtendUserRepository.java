package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;

import java.util.List;
import java.util.Map;

public interface ExtendUserRepository {
    List<User> searchUser(Map<String, String> queries);
    List<User> searchUsersForPost(String pattern, Integer limit);
}