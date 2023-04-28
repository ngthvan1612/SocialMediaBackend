package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.CreateAnnounceRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.GetAnnounceResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.ListAnnounceResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.UpdateAnnounceRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.Map;

public interface AnnounceService {
    SuccessResponse createAnnounce(CreateAnnounceRequest request);

    GetAnnounceResponse getAnnounceById(Integer id);

    ListAnnounceResponse searchAnnounces(Map<String, String> queries);

    SuccessResponse updateAnnounce(UpdateAnnounceRequest request);

    SuccessResponse deleteAnnounce(Integer id);

    void onCreatedNewPost(Integer postId);
}
