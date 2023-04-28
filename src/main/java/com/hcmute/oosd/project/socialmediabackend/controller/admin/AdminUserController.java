package com.hcmute.oosd.project.socialmediabackend.controller.admin;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces.UserService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    public AdminUserController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchUser(
            @AuthenticationPrincipal User loggingInUser,
            @RequestParam Map<String, String> queries
    ) {
        ListUserResponse listUserResponse = this.userService.searchUsers(queries, loggingInUser);
        return listUserResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getUser(
            @AuthenticationPrincipal User loggingInUser,
            @PathVariable Integer id
    ) {
        GetUserResponse getUserResponse = this.userService.getUserById(id, loggingInUser);
        return getUserResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createUser(
            @RequestBody CreateUserRequest request
    ) {
        SuccessResponse createUserResponse = this.userService.createUser(request);
        return createUserResponse;
    }

    @PutMapping("{id}/update-avatar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUserAvatar(
            @PathVariable Integer id,
            @RequestParam("avatarFile") MultipartFile avatarFile
    ) throws IOException {
        UpdateUserAvatarRequest request = new UpdateUserAvatarRequest();

        request.setUserId(id);
        request.setAvatarBufferByteArray(avatarFile.getBytes());
        request.setUploadFileName(avatarFile.getOriginalFilename());

        SuccessResponse updateUserAvatarResponse = this.userService.updateAvatarById(request);
        return updateUserAvatarResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUser(
            @PathVariable Integer id,
            @RequestBody UpdateUserRequest request
    ) {
        request.setUserId(id);
        SuccessResponse updateUserResponse = this.userService.updateUser(request);
        return updateUserResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteUser(
            @PathVariable Integer id
    ) {
        SuccessResponse updateUserResponse = this.userService.deleteUser(id);
        return updateUserResponse;
    }
}
