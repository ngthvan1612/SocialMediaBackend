package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces.FollowerService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.Map;

@CrossOrigin("*")
@RestController
// hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/follower")
public class CommonFollowerController {

    @Autowired
    private FollowerService followerService;

    public CommonFollowerController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchFollower(
            @RequestParam Map<String, String> queries) {
        ListFollowerResponse listFollowerResponse = this.followerService.searchFollowers(queries);
        return listFollowerResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getFollower(
            @PathVariable Integer id) {
        GetFollowerResponse getFollowerResponse = this.followerService.getFollowerById(id);
        return getFollowerResponse;
    }

    @PostMapping("toggle")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract toggle(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ToggleFollowerRequest request) {
        request.setUserId(user.getId());
        GetFollowerResponse getFollowerResponse = this.followerService.getFollowerByUserIdAndFollowerId(request);
        return getFollowerResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateFollower(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateFollowerRequest request) {
        request.setFollowerId(id);
        SuccessfulResponse updateFollowerResponse = this.followerService.updateFollower(request);
        return updateFollowerResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteFollower(
            @PathVariable Integer id) {
        SuccessfulResponse updateFollowerResponse = this.followerService.deleteFollower(id);
        return updateFollowerResponse;
    }


}
