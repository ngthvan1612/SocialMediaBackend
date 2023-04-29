package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.CreateGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.GetGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.ListGroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.UpdateGroupMessageRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.GroupMessageService;
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
@RequestMapping("api/common/group-message")
public class CommonGroupMessageController {

    @Autowired
    private GroupMessageService groupMessageService;

    public CommonGroupMessageController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchGroupMessage(
            @RequestParam Map<String, String> queries
    ) {
        ListGroupMessageResponse listGroupMessageResponse = this.groupMessageService.searchGroupMessages(queries);
        return listGroupMessageResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getGroupMessage(
            @PathVariable Integer id
    ) {
        GetGroupMessageResponse getGroupMessageResponse = this.groupMessageService.getGroupMessageById(id);
        return getGroupMessageResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createGroupMessage(
            @RequestBody @Valid CreateGroupMessageRequest request
    ) {
        SuccessResponse createGroupMessageResponse = this.groupMessageService.createGroupMessage(request);
        return createGroupMessageResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateGroupMessage(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateGroupMessageRequest request
    ) {
        request.setGroupMessageId(id);
        SuccessResponse updateGroupMessageResponse = this.groupMessageService.updateGroupMessage(request);
        return updateGroupMessageResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteGroupMessage(
            @PathVariable Integer id
    ) {
        SuccessResponse updateGroupMessageResponse = this.groupMessageService.deleteGroupMessage(id);
        return updateGroupMessageResponse;
    }
}
