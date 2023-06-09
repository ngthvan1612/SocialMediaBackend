package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.CreateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.GetUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.ListUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.UpdateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.UserGroupMessageService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
// hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/user-group-message")
public class CommonUserGroupMessageController {

    @Autowired
    private UserGroupMessageService userGroupMessageService;

    public CommonUserGroupMessageController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchUserGroupMessage(
            @RequestParam Map<String, String> queries) {
        ResponseBaseAbstract listUserGroupMessageResponse = this.userGroupMessageService
                .searchUserGroupMessages(queries);
        return listUserGroupMessageResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getUserGroupMessage(
            @PathVariable Integer id) {
        ResponseBaseAbstract getUserGroupMessageResponse = this.userGroupMessageService.getUserGroupMessageById(id);
        return getUserGroupMessageResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createUserGroupMessage(
            @RequestBody @Valid CreateUserGroupMessageRequest request) {
        ResponseBaseAbstract createUserGroupMessageResponse = this.userGroupMessageService
                .createUserGroupMessage(request);
        return createUserGroupMessageResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUserGroupMessage(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateUserGroupMessageRequest request) {
        request.setUserGroupMessageId(id);
        ResponseBaseAbstract updateUserGroupMessageResponse = this.userGroupMessageService
                .updateUserGroupMessage(request);
        return updateUserGroupMessageResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteUserGroupMessage(
            @PathVariable Integer id) {
        ResponseBaseAbstract updateUserGroupMessageResponse = this.userGroupMessageService.deleteUserGroupMessage(id);
        return updateUserGroupMessageResponse;
    }
}
