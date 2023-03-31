package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface UserService {
    SuccessfulResponse createUser(CreateUserRequest request);

    GetUserResponse getUserById(Integer id);

    ListUserResponse searchUsers(Map<String, String> queries);

    SearchUsersForPostResponse searchUsersForPost(String pattern, Integer limit);

    SuccessfulResponse updateUser(UpdateUserRequest request);

    SuccessfulResponse deleteUser(Integer id);

    SuccessfulResponse updateAvatarById(UpdateUserAvatarRequest request);


    LoginResponse authenticate(LoginRequest request);

    SuccessfulResponse registerUser(RegisterUserRequest registerUserRequest);

}
