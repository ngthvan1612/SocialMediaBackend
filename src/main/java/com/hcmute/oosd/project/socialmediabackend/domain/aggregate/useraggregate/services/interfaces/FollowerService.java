package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.*;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface FollowerService {
    SuccessfulResponse createFollower(CreateFollowerRequest request);

    GetFollowerResponse getFollowerById(Integer id);

    ListFollowerResponse searchFollowers(Map<String, String> queries);

    SuccessfulResponse updateFollower(UpdateFollowerRequest request);

    SuccessfulResponse deleteFollower(Integer id);

    GetFollowerResponse getFollowerByUserIdAndFollowerId(ToggleFollowerRequest request);

    SuccessfulResponse getListPeoplesFollowMe(Integer userid);

    SuccessfulResponse getListPeoplesFollowed(Integer userid);
}
