package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.CreatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.GetPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.ListPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.UpdatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface PostService {
    SuccessfulResponse createPost(CreatePostRequest request);

    GetPostResponse getPostById(Integer id);

    ListPostResponse searchPosts(Map<String, String> queries);

    SuccessfulResponse updatePost(UpdatePostRequest request);

    SuccessfulResponse deletePost(Integer id);
    SuccessfulResponse toogleLikePost(CreateReactionRequest request);

}
