package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListStoryResponse extends SuccessfulResponse {
    public ListStoryResponse(List<StoryResponse> storyResponses) {
        super();
        this.setData(storyResponses);
    }
}
