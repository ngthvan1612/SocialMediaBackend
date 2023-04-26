package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.model;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import lombok.Data;

@Data
public class CreatedPostNotification {
    private Post post;
    private String message;

    public CreatedPostNotification() { }
}
