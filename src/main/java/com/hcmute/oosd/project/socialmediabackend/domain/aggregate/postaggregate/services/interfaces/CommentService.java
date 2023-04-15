package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.CreateCommentRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.GetCommentResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.ListCommentResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.UpdateCommentRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface CommentService {
    SuccessfulResponse createComment(CreateCommentRequest request);

    GetCommentResponse getCommentById(Integer id);

    ListCommentResponse searchComments(Map<String, String> queries);

    SuccessfulResponse updateComment(UpdateCommentRequest request);

    SuccessfulResponse deleteComment(Integer id, Integer userId);


}
