package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchUsersForPost {
    private Integer id;
    private String username;

    public SearchUsersForPost() { }

    public SearchUsersForPost(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
