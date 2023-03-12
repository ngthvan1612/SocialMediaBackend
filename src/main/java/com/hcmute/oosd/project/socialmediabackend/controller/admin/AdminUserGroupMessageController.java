package com.hcmute.oosd.project.socialmediabackend.controller.admin;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.CreateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.GetUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.ListUserGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.UpdateUserGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.UserGroupMessageService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/user-group-message")
public class AdminUserGroupMessageController {

    @Autowired
    private UserGroupMessageService userGroupMessageService;

    public AdminUserGroupMessageController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchUserGroupMessage(
            @RequestParam Map<String, String> queries
    ) {
        ListUserGroupMessageResponse listUserGroupMessageResponse = this.userGroupMessageService.searchUserGroupMessages(queries);
        return listUserGroupMessageResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getUserGroupMessage(
            @PathVariable Integer id
    ) {
        GetUserGroupMessageResponse getUserGroupMessageResponse = this.userGroupMessageService.getUserGroupMessageById(id);
        return getUserGroupMessageResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createUserGroupMessage(
            @RequestBody CreateUserGroupMessageRequest request
    ) {
        SuccessfulResponse createUserGroupMessageResponse = this.userGroupMessageService.createUserGroupMessage(request);
        return createUserGroupMessageResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUserGroupMessage(
            @PathVariable Integer id,
            @RequestBody UpdateUserGroupMessageRequest request
    ) {
        request.setUserGroupMessageId(id);
        SuccessfulResponse updateUserGroupMessageResponse = this.userGroupMessageService.updateUserGroupMessage(request);
        return updateUserGroupMessageResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteUserGroupMessage(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updateUserGroupMessageResponse = this.userGroupMessageService.deleteUserGroupMessage(id);
        return updateUserGroupMessageResponse;
    }
}
