package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces.UserService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@CrossOrigin("*")
@RestController
// hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/user")
public class CommonUserController {

    @Autowired
    private UserService userService;

    public CommonUserController() {

    }

    @GetMapping("search-users-for-post")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchUsersForPost(
            @RequestParam("username.contains") String username,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        ResponseBaseAbstract searchUsersForPostResponse = this.userService.searchUsersForPost(username, limit);
        return searchUsersForPostResponse;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchUser(
            @AuthenticationPrincipal User loggingInUser,
            @RequestParam Map<String, String> queries) {
        ResponseBaseAbstract listUserResponse = this.userService.searchUsers(queries, loggingInUser);
        return listUserResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getUser(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Integer id) {
        ResponseBaseAbstract getUserResponse = this.userService.getUserById(id, currentUser);
        return getUserResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createUser(
            @RequestBody @Valid CreateUserRequest request) {
        ResponseBaseAbstract createUserResponse = this.userService.createUser(request);
        return createUserResponse;
    }

    @PutMapping("/update-avatar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUserAvatar(
            @AuthenticationPrincipal User user,
            @RequestParam("avatarFile") MultipartFile avatarFile) throws IOException {
        UpdateUserAvatarRequest request = new UpdateUserAvatarRequest();

        request.setUserId(user.getId());
        request.setAvatarBufferByteArray(avatarFile.getBytes());
        request.setUploadFileName(avatarFile.getOriginalFilename());

        ResponseBaseAbstract updateUserAvatarResponse = this.userService.updateAvatarById(request);
        return updateUserAvatarResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUser(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateUserRequest request) {
        request.setUserId(id);
        ResponseBaseAbstract updateUserResponse = this.userService.updateUser(request);
        return updateUserResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteUser(
            @PathVariable Integer id) {
        ResponseBaseAbstract updateUserResponse = this.userService.deleteUser(id);
        return updateUserResponse;
    }

    @GetMapping("suggestion-users-for-me")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getListSuggestionsForMe(
            @AuthenticationPrincipal User loggingInUser) {
        ResponseBaseAbstract getListSuggestionsForMeResponse = this.userService.getSuggestionsForMe(loggingInUser);
        return getListSuggestionsForMeResponse;
    }
}
