package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetStoryResponse extends SuccessfulResponse {
    public GetStoryResponse(StoryResponse storyResponse) {
        super();
        this.setData(storyResponse);
    }
}
