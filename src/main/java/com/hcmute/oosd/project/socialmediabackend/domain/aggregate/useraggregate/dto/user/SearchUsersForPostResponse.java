package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class SearchUsersForPostResponse extends SuccessfulResponse {
    public SearchUsersForPostResponse(List<SearchUsersForPost> usersForPosts) {
        super();
        this.setData(usersForPosts);
    }
}
