package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetUserTagFriendPostResponse extends SuccessResponse {
    public GetUserTagFriendPostResponse(UserTagFriendPostResponse userTagFriendPostResponse) {
        super();
        this.setData(userTagFriendPostResponse);
    }
}
