package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce;

import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;

public class GetAnnounceResponse extends SuccessResponse {
    public GetAnnounceResponse(AnnounceResponse announceResponse) {
        super();
        this.setData(announceResponse);
    }
}
