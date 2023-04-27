package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.CreateStoryRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.GetStoryResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.ListStoryResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.UpdateStoryRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface StoryService {
    SuccessResponse createStory(CreateStoryRequest request);

    GetStoryResponse getStoryById(Integer id);

    ListStoryResponse searchStorys(Map<String, String> queries);

    SuccessResponse updateStory(UpdateStoryRequest request);

    SuccessResponse deleteStory(Integer id);


}
