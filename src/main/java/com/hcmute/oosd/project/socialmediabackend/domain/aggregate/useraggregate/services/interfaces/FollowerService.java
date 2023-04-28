package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.*;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import java.util.Map;

public interface FollowerService {
    ResponseBaseAbstract createFollower(CreateFollowerRequest request);

    GetFollowerResponse getFollowerById(Integer id);

    ListFollowerResponse searchFollowers(Map<String, String> queries);

    ResponseBaseAbstract updateFollower(UpdateFollowerRequest request);

    ResponseBaseAbstract deleteFollower(Integer id);

    GetFollowerResponse getFollowerByUserIdAndFollowerId(ToggleFollowerRequest request);

    ResponseBaseAbstract getListPeoplesFollowMe(Integer userid);

    ResponseBaseAbstract getListPeoplesFollowed(Integer userid);
}
