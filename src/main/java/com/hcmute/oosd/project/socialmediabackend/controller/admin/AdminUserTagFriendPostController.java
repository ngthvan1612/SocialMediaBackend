package com.hcmute.oosd.project.socialmediabackend.controller.admin;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.CreateUserTagFriendPostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.GetUserTagFriendPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.ListUserTagFriendPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.UpdateUserTagFriendPostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.UserTagFriendPostService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/user-tag-friend-post")
public class AdminUserTagFriendPostController {

    @Autowired
    private UserTagFriendPostService userTagFriendPostService;

    public AdminUserTagFriendPostController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchUserTagFriendPost(
            @RequestParam Map<String, String> queries
    ) {
        ListUserTagFriendPostResponse listUserTagFriendPostResponse = this.userTagFriendPostService.searchUserTagFriendPosts(queries);
        return listUserTagFriendPostResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getUserTagFriendPost(
            @PathVariable Integer id
    ) {
        GetUserTagFriendPostResponse getUserTagFriendPostResponse = this.userTagFriendPostService.getUserTagFriendPostById(id);
        return getUserTagFriendPostResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createUserTagFriendPost(
            @RequestBody CreateUserTagFriendPostRequest request
    ) {
        SuccessfulResponse createUserTagFriendPostResponse = this.userTagFriendPostService.createUserTagFriendPost(request);
        return createUserTagFriendPostResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUserTagFriendPost(
            @PathVariable Integer id,
            @RequestBody UpdateUserTagFriendPostRequest request
    ) {
        request.setUserTagFriendPostId(id);
        SuccessfulResponse updateUserTagFriendPostResponse = this.userTagFriendPostService.updateUserTagFriendPost(request);
        return updateUserTagFriendPostResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteUserTagFriendPost(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updateUserTagFriendPostResponse = this.userTagFriendPostService.deleteUserTagFriendPost(id);
        return updateUserTagFriendPostResponse;
    }
}
