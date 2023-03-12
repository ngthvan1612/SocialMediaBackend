package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.entities.Announce;

import java.util.List;
import java.util.Map;

public interface ExtendAnnounceRepository {
    List<Announce> searchAnnounce(Map<String, String> queries);
}