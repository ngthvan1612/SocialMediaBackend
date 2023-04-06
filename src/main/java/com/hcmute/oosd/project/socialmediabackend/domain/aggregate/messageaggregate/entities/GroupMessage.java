package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_groupMessage")
public class GroupMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "createdAt")
    private Date createdAt = new Date();

    @Column(name = "lastUpdatedAt")
    private Date lastUpdatedAt = new Date();

    @Column(name = "deletedAt")
    private Date deletedAt;
    @Column(name = "isRead")
    private Boolean isRead=false;
    @Column(name = "displayName")
    private String displayName;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;


    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Message> messages;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<UserGroupMessage> userGroupMessages;

}
