package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.enums.UserGender;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.enums.UserRole;
import lombok.Data;

public class UpdateUserPasswordRequest {
    @JsonIgnore
    private Integer userId;
    private String password;

    public void setUserId(Integer id) {
        this.userId = id;
    }
    public void setUserPassword(String password) {
        this.password = password;
    }


    public Integer getUserId() {
        return userId;
    }

    public CharSequence getPassword() {
        return password;
    }
}
