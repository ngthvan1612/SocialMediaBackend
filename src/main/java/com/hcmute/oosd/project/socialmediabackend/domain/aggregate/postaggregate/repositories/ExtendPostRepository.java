package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Post;

import java.util.List;
import java.util.Map;

public interface ExtendPostRepository {
    List<Post> searchPost(Map<String, String> queries);
}