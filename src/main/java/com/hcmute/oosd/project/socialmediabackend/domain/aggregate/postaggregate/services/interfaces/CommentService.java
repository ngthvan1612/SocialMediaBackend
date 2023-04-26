package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.CreateCommentRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.GetCommentResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.ListCommentResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.UpdateCommentRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface CommentService {
    SuccessResponse createComment(CreateCommentRequest request);

    GetCommentResponse getCommentById(Integer id);

    ListCommentResponse searchComments(Map<String, String> queries);

    SuccessResponse updateComment(UpdateCommentRequest request);

    SuccessResponse deleteComment(Integer id);


}
