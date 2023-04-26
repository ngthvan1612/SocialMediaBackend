package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

import java.util.List;

public class ListAnnounceResponse extends SuccessResponse {
    public ListAnnounceResponse(List<AnnounceResponse> announceResponses) {
        super();
        this.setData(announceResponses);
    }
}
