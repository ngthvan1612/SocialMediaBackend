package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.CreatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.GetPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.ListPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.UpdatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface PostService {
    ResponseBaseAbstract createPost(CreatePostRequest request);

    ResponseBaseAbstract getPostById(Integer id);

    ResponseBaseAbstract searchPosts(Map<String, String> queries);

    ResponseBaseAbstract updatePost(UpdatePostRequest request);

    SuccessResponse deletePost(Integer id);
    SuccessResponse toogleLikePost(CreateReactionRequest request);

}
