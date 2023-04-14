package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface UserService {
    SuccessfulResponse getSuggestionsForMe(User loggingInUser);

    SuccessfulResponse createUser(CreateUserRequest request);

    GetUserResponse getUserById(Integer id, User loggingInUser);

    ListUserResponse searchUsers(Map<String, String> queries, User loggingInUser);

    SearchUsersForPostResponse searchUsersForPost(String pattern, Integer limit);

    SuccessfulResponse updateUser(UpdateUserRequest request);

    SuccessfulResponse deleteUser(Integer id);

    SuccessfulResponse updateAvatarById(UpdateUserAvatarRequest request);


    LoginResponse authenticate(LoginRequest request);

    SuccessfulResponse registerUser(RegisterUserRequest registerUserRequest);

}
