package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import java.util.Map;

public interface UserService {
    ResponseBaseAbstract getSuggestionsForMe(User loggingInUser);

    ResponseBaseAbstract createUser(CreateUserRequest request);

    ResponseBaseAbstract getUserById(Integer id, User loggingInUser);

    ResponseBaseAbstract searchUsers(Map<String, String> queries, User loggingInUser);

    ResponseBaseAbstract searchUsersForPost(String pattern, Integer limit);

    ResponseBaseAbstract updateUser(UpdateUserRequest request);
    ResponseBaseAbstract changePassword(UpdateUserPasswordRequest request);

    ResponseBaseAbstract deleteUser(Integer id);

    ResponseBaseAbstract updateAvatarById(UpdateUserAvatarRequest request);

    ResponseBaseAbstract authenticate(LoginRequest request);

    ResponseBaseAbstract registerUser(RegisterUserRequest registerUserRequest);

}
