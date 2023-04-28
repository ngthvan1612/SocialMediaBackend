package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.MessageService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.ListUserResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
// hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/message")
public class CommonMessageController {

    @Autowired
    private MessageService messageService;

    public CommonMessageController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchMessage(
            @RequestParam Map<String, String> queries) {
        ResponseBaseAbstract listMessageResponse = this.messageService.searchMessages(queries);
        return listMessageResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getMessage(
            @PathVariable Integer id) {
        ResponseBaseAbstract getMessageResponse = this.messageService.getMessageById(id);
        return getMessageResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createMessage(
            @RequestBody @Valid CreateMessageRequest request) {
        ResponseBaseAbstract createMessageResponse = this.messageService.createMessage(request);
        return createMessageResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateMessage(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateMessageRequest request) {
        request.setMessageId(id);
        ResponseBaseAbstract updateMessageResponse = this.messageService.updateMessage(request);
        return updateMessageResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteMessage(
            @PathVariable Integer id) {
        ResponseBaseAbstract updateMessageResponse = this.messageService.deleteMessage(id);
        return updateMessageResponse;
    }

    @GetMapping("from/{usera}/to/{userb}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getMessagesFromUser(
            @PathVariable Integer usera, @PathVariable Integer userb) {
        ResponseBaseAbstract listMessageResponse = this.messageService.getMessageFromOneToOne(usera, userb);
        return listMessageResponse;

    }


    @PostMapping("list-message")
    public ResponseBaseAbstract getListMessageWithAnotherPerson(
            @RequestBody @Valid GetListMessageWithAnotherPersonRequest request,
            @AuthenticationPrincipal User user) {
        request.setUserId(user.getId());
        return this.messageService.getListMessageWithAnotherPerson(request);

    }

    @GetMapping("{userId}/list-user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getAllUserHaveBeenChat(
            @PathVariable Integer userId) {
        ResponseBaseAbstract listUserResponse = this.messageService.getAllUserHaveBeenChat(userId);
        return listUserResponse;

    }
}
