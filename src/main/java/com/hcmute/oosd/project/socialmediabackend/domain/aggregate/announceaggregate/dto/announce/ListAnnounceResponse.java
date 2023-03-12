package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

import java.util.List;

public class ListAnnounceResponse extends SuccessfulResponse {
    public ListAnnounceResponse(List<AnnounceResponse> announceResponses) {
        super();
        this.setData(announceResponses);
    }
}
