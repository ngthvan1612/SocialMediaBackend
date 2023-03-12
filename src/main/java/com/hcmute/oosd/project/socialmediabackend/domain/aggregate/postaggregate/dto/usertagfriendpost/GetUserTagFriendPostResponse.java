package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetUserTagFriendPostResponse extends SuccessfulResponse {
    public GetUserTagFriendPostResponse(UserTagFriendPostResponse userTagFriendPostResponse) {
        super();
        this.setData(userTagFriendPostResponse);
    }
}
