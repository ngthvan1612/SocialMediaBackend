package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.model.postcontent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PostContentFactory {
    private static final Gson gsonSerializer = new GsonBuilder()
            .create();

    public static PostContentBase PostContentFactory(PostContentType type) {
        if (type == PostContentType.TI_POST) {
            return new TextAndImagePostContent();
        }
        return null;
    }

    public static PostContentBase fromJson(String json) {
        return gsonSerializer.fromJson(json, TextAndImagePostContent.class);
    }

    public static <T extends PostContentBase> String toJson(T postContent) {
        return gsonSerializer.toJson(postContent);
    }
}
