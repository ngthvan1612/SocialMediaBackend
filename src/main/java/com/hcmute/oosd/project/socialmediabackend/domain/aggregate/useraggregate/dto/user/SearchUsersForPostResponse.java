package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import java.util.List;

import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

public class SearchUsersForPostResponse extends ResponseBaseAbstract {
    public SearchUsersForPostResponse(List<SearchUsersForPost> usersForPosts) {
        super();
        this.setData(usersForPosts);
    }
}
