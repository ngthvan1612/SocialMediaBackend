package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.enums.UserGender;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.enums.UserRole;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class CreateUserRequest {

    @NotEmpty(message = "tên người dùng không được trống")
    private String username;

    @NotEmpty(message = "mật khẩu không được trống")
    private String password;


    private String displayName;


    private Date birthday;


    private String avatar;


    private String profile;


    private UserGender gender;


    private UserRole role;


}