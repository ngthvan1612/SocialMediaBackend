package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface UserService {
    SuccessResponse getSuggestionsForMe(User loggingInUser);

    SuccessResponse createUser(CreateUserRequest request);

    GetUserResponse getUserById(Integer id, User loggingInUser);

    ListUserResponse searchUsers(Map<String, String> queries, User loggingInUser);

    SearchUsersForPostResponse searchUsersForPost(String pattern, Integer limit);

    SuccessResponse updateUser(UpdateUserRequest request);

    SuccessResponse deleteUser(Integer id);

    SuccessResponse updateAvatarById(UpdateUserAvatarRequest request);


    LoginResponse authenticate(LoginRequest request);

    SuccessResponse registerUser(RegisterUserRequest registerUserRequest);

}
