package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Comment;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;
import java.util.Map;

public interface CommentService {
    ResponseBaseAbstract createComment(CreateCommentRequest request);

    ResponseBaseAbstract getCommentById(Integer id);

    ResponseBaseAbstract searchComments(Map<String, String> queries);

    ResponseBaseAbstract updateComment(UpdateCommentRequest request);

    ResponseBaseAbstract deleteComment(Integer id, Integer userId);

    ResponseBaseAbstract getByPost(Integer id);

    ResponseBaseAbstract getByComment(Integer id);

}
