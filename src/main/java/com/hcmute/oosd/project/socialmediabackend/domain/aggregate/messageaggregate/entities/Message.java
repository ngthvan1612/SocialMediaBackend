package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "createdAt")
    private Date createdAt = new Date();

    @Column(name = "lastUpdatedAt")
    private Date lastUpdatedAt = new Date();

    @Column(name = "deletedAt")
    private Date deletedAt;

    @Length(max = 65536)
    @Column(name = "content")
    private String content;

    @Column(name = "isRead")
    private Boolean isRead = false;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupMessage group;



}
