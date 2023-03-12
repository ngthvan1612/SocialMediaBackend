package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.CreateUserTagFriendPostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.GetUserTagFriendPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.ListUserTagFriendPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.UpdateUserTagFriendPostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface UserTagFriendPostService {
    SuccessfulResponse createUserTagFriendPost(CreateUserTagFriendPostRequest request);

    GetUserTagFriendPostResponse getUserTagFriendPostById(Integer id);

    ListUserTagFriendPostResponse searchUserTagFriendPosts(Map<String, String> queries);

    SuccessfulResponse updateUserTagFriendPost(UpdateUserTagFriendPostRequest request);

    SuccessfulResponse deleteUserTagFriendPost(Integer id);


}
