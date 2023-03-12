package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.enums.UserGender;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "createdAt")
    private Date createdAt = new Date();

    @Column(name = "lastUpdatedAt")
    private Date lastUpdatedAt = new Date();

    @Column(name = "deletedAt")
    private Date deletedAt;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "displayName")
    private String displayName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "profile")
    private String profile;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Follower> followers;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Follower> followers;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Post> posts;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<UserTagFriendPost> userTagFriendPosts;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Reaction> reactions;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Comment> comments;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Story> storys;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Announce> announces;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Message> messages;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Message> messages;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<UserGroupMessage> userGroupMessages;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<GroupMessage> groupMessages;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
