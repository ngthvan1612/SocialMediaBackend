package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListStoryResponse extends SuccessResponse {
    public ListStoryResponse(List<StoryResponse> storyResponses) {
        super();
        this.setData(storyResponses);
    }
}
