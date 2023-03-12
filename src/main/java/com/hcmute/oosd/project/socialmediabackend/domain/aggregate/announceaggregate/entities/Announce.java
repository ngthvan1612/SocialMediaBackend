package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.entities;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_announce")
public class Announce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "createdAt")
    private Date createdAt = new Date();

    @Column(name = "lastUpdatedAt")
    private Date lastUpdatedAt = new Date();

    @Column(name = "deletedAt")
    private Date deletedAt;

    @Column(name = "content")
    private String content;

    @Column(name = "seen")
    private Boolean seen;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
