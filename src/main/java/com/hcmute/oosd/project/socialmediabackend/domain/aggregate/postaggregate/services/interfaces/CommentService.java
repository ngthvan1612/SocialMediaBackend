package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Comment;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;
import java.util.Map;

public interface CommentService {
    SuccessfulResponse createComment(CreateCommentRequest request);

    GetCommentResponse getCommentById(Integer id);

    ListCommentResponse searchComments(Map<String, String> queries);

    SuccessfulResponse updateComment(UpdateCommentRequest request);

    SuccessfulResponse deleteComment(Integer id, Integer userId);

    ListCommentPostResponse getByPost(Integer id);
    List<Comment> getByComment(Integer id);


}
