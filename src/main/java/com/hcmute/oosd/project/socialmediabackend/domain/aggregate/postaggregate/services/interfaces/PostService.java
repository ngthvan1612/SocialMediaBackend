package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.CreatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.UpdatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import java.util.Map;

public interface PostService {
    ResponseBaseAbstract createPost(CreatePostRequest request);

    ResponseBaseAbstract getPostById(Integer id, Integer uid);

    ResponseBaseAbstract searchPosts(Map<String, String> queries, Integer uid);

    ResponseBaseAbstract updatePost(UpdatePostRequest request);

    ResponseBaseAbstract toogleLikePost(CreateReactionRequest request);

    ResponseBaseAbstract deletePost(Integer id);

}
