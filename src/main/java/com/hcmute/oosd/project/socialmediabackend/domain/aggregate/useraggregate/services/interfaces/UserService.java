package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import java.util.Map;

public interface UserService {
    ResponseBaseAbstract createUser(CreateUserRequest request);

    GetUserResponse getUserById(Integer id, User loggingInUser);

    ListUserResponse searchUsers(Map<String, String> queries, User loggingInUser);

    SearchUsersForPostResponse searchUsersForPost(String pattern, Integer limit);

    ResponseBaseAbstract updateUser(UpdateUserRequest request);

    ResponseBaseAbstract deleteUser(Integer id);

    ResponseBaseAbstract updateAvatarById(UpdateUserAvatarRequest request);

    LoginResponse authenticate(LoginRequest request);

    ResponseBaseAbstract registerUser(RegisterUserRequest registerUserRequest);

}
