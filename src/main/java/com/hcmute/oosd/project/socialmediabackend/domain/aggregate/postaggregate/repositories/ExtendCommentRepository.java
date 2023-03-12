package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Comment;

import java.util.List;
import java.util.Map;

public interface ExtendCommentRepository {
    List<Comment> searchComment(Map<String, String> queries);
}