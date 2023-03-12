package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.CreateFollowerRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.GetFollowerResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.ListFollowerResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.UpdateFollowerRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface FollowerService {
    SuccessfulResponse createFollower(CreateFollowerRequest request);

    GetFollowerResponse getFollowerById(Integer id);

    ListFollowerResponse searchFollowers(Map<String, String> queries);

    SuccessfulResponse updateFollower(UpdateFollowerRequest request);

    SuccessfulResponse deleteFollower(Integer id);


}
