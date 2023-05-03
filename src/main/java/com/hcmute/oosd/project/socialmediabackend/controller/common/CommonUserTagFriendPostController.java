package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.CreateUserTagFriendPostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.GetUserTagFriendPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.ListUserTagFriendPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.UpdateUserTagFriendPostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.UserTagFriendPostService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/user-tag-friend-post")
public class CommonUserTagFriendPostController {

    @Autowired
    private UserTagFriendPostService userTagFriendPostService;

    public CommonUserTagFriendPostController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchUserTagFriendPost(
            @RequestParam Map<String, String> queries
    ) {
        ResponseBaseAbstract listUserTagFriendPostResponse = this.userTagFriendPostService.searchUserTagFriendPosts(queries);
        return listUserTagFriendPostResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getUserTagFriendPost(
            @PathVariable Integer id
    ) {
        ResponseBaseAbstract getUserTagFriendPostResponse = this.userTagFriendPostService.getUserTagFriendPostById(id);
        return getUserTagFriendPostResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createUserTagFriendPost(
            @RequestBody @Valid CreateUserTagFriendPostRequest request
    ) {
        ResponseBaseAbstract createUserTagFriendPostResponse = this.userTagFriendPostService.createUserTagFriendPost(request);
        return createUserTagFriendPostResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUserTagFriendPost(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateUserTagFriendPostRequest request
    ) {
        request.setUserTagFriendPostId(id);
        ResponseBaseAbstract updateUserTagFriendPostResponse = this.userTagFriendPostService.updateUserTagFriendPost(request);
        return updateUserTagFriendPostResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteUserTagFriendPost(
            @PathVariable Integer id
    ) {
        ResponseBaseAbstract updateUserTagFriendPostResponse = this.userTagFriendPostService.deleteUserTagFriendPost(id);
        return updateUserTagFriendPostResponse;
    }
}
