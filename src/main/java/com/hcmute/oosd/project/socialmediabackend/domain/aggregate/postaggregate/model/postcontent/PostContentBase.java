package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.model.postcontent;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.PostContentMetaData;
import lombok.Data;

@Data
public abstract class PostContentBase {
    private PostContentMetaData meta;

    public abstract boolean validate(Boolean throwWhenFailed) ;
}
