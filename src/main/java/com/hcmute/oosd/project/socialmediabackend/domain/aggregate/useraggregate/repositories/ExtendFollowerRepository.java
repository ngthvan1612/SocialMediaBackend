package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.Follower;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ExtendFollowerRepository {
    List<Follower> searchFollower(Map<String, String> queries);
}