package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListUserTagFriendPostResponse extends SuccessfulResponse {
    public ListUserTagFriendPostResponse(List<UserTagFriendPostResponse> userTagFriendPostResponses) {
        super();
        this.setData(userTagFriendPostResponses);
    }
}
