package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.services.interfaces;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.CreateAnnounceRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.GetAnnounceResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.ListAnnounceResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.UpdateAnnounceRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.Map;

public interface AnnounceService {
    SuccessfulResponse createAnnounce(CreateAnnounceRequest request);

    GetAnnounceResponse getAnnounceById(Integer id);

    ListAnnounceResponse searchAnnounces(Map<String, String> queries);

    SuccessfulResponse updateAnnounce(UpdateAnnounceRequest request);

    SuccessfulResponse deleteAnnounce(Integer id);


}
