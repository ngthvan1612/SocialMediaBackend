package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_userGroupMessage")
public class UserGroupMessage {

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
    @JoinColumn(name = "group_id")
    private GroupMessage group;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private User member;


}
