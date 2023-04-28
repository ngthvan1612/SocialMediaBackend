package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListUserTagFriendPostResponse extends SuccessResponse {
    public ListUserTagFriendPostResponse(List<UserTagFriendPostResponse> userTagFriendPostResponses) {
        super();
        this.setData(userTagFriendPostResponses);
    }
}
