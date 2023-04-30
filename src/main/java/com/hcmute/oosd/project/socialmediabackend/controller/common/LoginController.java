package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.LoginRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces.UserService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth/login")
public class LoginController {
    @Autowired
    private UserService userService;

    public LoginController() {

    }

    @PostMapping
    public ResponseBaseAbstract login(@RequestBody LoginRequest request) {
        ResponseBaseAbstract response = this.userService.authenticate(request);
        return response;
    }
}
