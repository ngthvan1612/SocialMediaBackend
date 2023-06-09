package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces.FollowerService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        ResponseBaseAbstract listFollowerResponse = this.followerService.searchFollowers(queries);
        return listFollowerResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getFollower(
            @PathVariable Integer id) {
        ResponseBaseAbstract getFollowerResponse = this.followerService.getFollowerById(id);
        return getFollowerResponse;
    }

    @PostMapping("toggle")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract toggle(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ToggleFollowerRequest request) {
        request.setUserId(user.getId());
        ResponseBaseAbstract getFollowerResponse = this.followerService.getFollowerByUserIdAndFollowerId(request);
        return getFollowerResponse;
    }

    @GetMapping("{id}/followers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract listPeoplesFollowMe( // lấy danh sách những người follow mình
            @PathVariable Integer id) {
        ResponseBaseAbstract successfulResponse = this.followerService.getListPeoplesFollowMe(id);
        return successfulResponse;
    }

    @GetMapping("{id}/following")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract listPeoplesFollowed( // lấy danh sách những người mình follow
            @PathVariable Integer id) {
        ResponseBaseAbstract successfulResponse = this.followerService.getListPeoplesFollowed(id);
        return successfulResponse;
    }

}
