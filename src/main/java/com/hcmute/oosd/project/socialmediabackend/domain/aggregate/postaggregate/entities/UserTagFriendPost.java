package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_userTagFriendPost")
public class UserTagFriendPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "createdAt")
    private Date createdAt = new Date();

    @Column(name = "lastUpdatedAt")
    private Date lastUpdatedAt = new Date();

    @Column(name = "deletedAt")
    private Date deletedAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    public UserTagFriendPost() { }

    public UserTagFriendPost(User friend, Post post) {
        this.friend = friend;
        this.post = post;
    }
}
