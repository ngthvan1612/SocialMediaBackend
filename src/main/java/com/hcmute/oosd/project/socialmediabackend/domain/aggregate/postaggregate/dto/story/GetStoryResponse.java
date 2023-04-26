package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetStoryResponse extends SuccessResponse {
    public GetStoryResponse(StoryResponse storyResponse) {
        super();
        this.setData(storyResponse);
    }
}
