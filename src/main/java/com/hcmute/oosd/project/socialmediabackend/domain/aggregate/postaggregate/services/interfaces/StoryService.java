package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.CreateStoryRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.GetStoryResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.ListStoryResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.UpdateStoryRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface StoryService {
    SuccessfulResponse createStory(CreateStoryRequest request);

    GetStoryResponse getStoryById(Integer id);

    ListStoryResponse searchStorys(Map<String, String> queries);

    SuccessfulResponse updateStory(UpdateStoryRequest request);

    SuccessfulResponse deleteStory(Integer id);


}
