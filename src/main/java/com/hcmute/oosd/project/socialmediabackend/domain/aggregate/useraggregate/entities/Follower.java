package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_follower")
public class Follower {

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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "follow_id")
    private User follow;


}
