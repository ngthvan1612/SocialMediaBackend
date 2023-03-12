package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Story;

import java.util.List;
import java.util.Map;

public interface ExtendStoryRepository {
    List<Story> searchStory(Map<String, String> queries);
}