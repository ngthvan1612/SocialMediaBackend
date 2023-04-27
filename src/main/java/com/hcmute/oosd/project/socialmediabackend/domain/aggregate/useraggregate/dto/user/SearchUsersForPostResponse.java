package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class SearchUsersForPostResponse extends SuccessResponse {
    public SearchUsersForPostResponse(List<SearchUsersForPost> usersForPosts) {
        super();
        this.setData(usersForPosts);
    }
}
