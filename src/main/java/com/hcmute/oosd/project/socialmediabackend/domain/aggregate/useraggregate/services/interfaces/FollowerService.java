package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.*;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface FollowerService {
    SuccessResponse createFollower(CreateFollowerRequest request);

    GetFollowerResponse getFollowerById(Integer id);

    ListFollowerResponse searchFollowers(Map<String, String> queries);

    SuccessResponse updateFollower(UpdateFollowerRequest request);

    SuccessResponse deleteFollower(Integer id);

    GetFollowerResponse getFollowerByUserIdAndFollowerId(ToggleFollowerRequest request);

    SuccessResponse getListPeoplesFollowMe(Integer userid);

    SuccessResponse getListPeoplesFollowed(Integer userid);
}
