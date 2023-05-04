package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.CreateUserTagFriendPostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.GetUserTagFriendPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.ListUserTagFriendPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.UpdateUserTagFriendPostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface UserTagFriendPostService {
    ResponseBaseAbstract createUserTagFriendPost(CreateUserTagFriendPostRequest request);

    ResponseBaseAbstract getUserTagFriendPostById(Integer id);

    ResponseBaseAbstract searchUserTagFriendPosts(Map<String, String> queries);

    ResponseBaseAbstract updateUserTagFriendPost(UpdateUserTagFriendPostRequest request);

    ResponseBaseAbstract deleteUserTagFriendPost(Integer id);


}
