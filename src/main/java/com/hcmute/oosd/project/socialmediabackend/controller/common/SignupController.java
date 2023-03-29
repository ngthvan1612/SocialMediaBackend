package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.LoginRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.LoginResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.RegisterUserRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces.UserService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth/signup")
public class SignupController {
    @Autowired
    private UserService userService;

    public SignupController() {

    }

    @PostMapping
    public SuccessfulResponse signup(@RequestBody RegisterUserRequest request) {
        SuccessfulResponse response = this.userService.registerUser(request);
        return response;
    }
}
