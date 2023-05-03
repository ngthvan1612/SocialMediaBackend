package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.CreateStoryRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.GetStoryResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.ListStoryResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.UpdateStoryRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface StoryService {
    ResponseBaseAbstract createStory(CreateStoryRequest request);

    ResponseBaseAbstract getStoryById(Integer id);

    ResponseBaseAbstract searchStorys(Map<String, String> queries);

    ResponseBaseAbstract updateStory(UpdateStoryRequest request);

    ResponseBaseAbstract deleteStory(Integer id);


}
