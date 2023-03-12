package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Reaction;

import java.util.List;
import java.util.Map;

public interface ExtendReactionRepository {
    List<Reaction> searchReaction(Map<String, String> queries);
}