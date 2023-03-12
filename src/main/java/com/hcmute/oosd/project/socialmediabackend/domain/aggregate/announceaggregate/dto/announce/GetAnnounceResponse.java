package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;

public class GetAnnounceResponse extends SuccessfulResponse {
    public GetAnnounceResponse(AnnounceResponse announceResponse) {
        super();
        this.setData(announceResponse);
    }
}
