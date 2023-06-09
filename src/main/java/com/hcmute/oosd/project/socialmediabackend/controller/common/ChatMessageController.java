package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.SocialMediaBackendApplication;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.GroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToGroup;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToOne;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.MessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.GroupMessageService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.MessageService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.types.ChatMessageOneToGroupType;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.types.ChatMessageOneToOneType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;

@Controller
public class ChatMessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageRepository messageRepository;
    private GroupMessageService groupmessageService;
    private GroupMessageResponse groupmessageResponse;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SocialMediaBackendApplication.class);
    @MessageMapping("/ws/secured/messenger")
    public void sendMessageOneToOne(@Payload ChatMessageOneToOne msg) {
        Integer sender = msg.getSenderId();
        Integer receiver = msg.getReceiverId();
        if(msg.getType() == ChatMessageOneToOneType.SEEN)
        {
            msg.setMessageAsRead();
            this.messageService.storeMessage(msg);
            // Update the message status on the receiver side
//            String receiverEndPoint = "/ws/secured/messenger/user-" + receiver;
//            simpMessagingTemplate.convertAndSend(receiverEndPoint, msg);
        }
        if (msg.getType() == ChatMessageOneToOneType.MESSAGE) {
            String message = msg.getMessage();
            msg.setCreatedAt(new Date());
            this.messageService.storeMessage(msg);
            logger.info(String.format("WS-INFO: %s send to %s: %s", sender, receiver, message));

            String receiverEndPoint = "/ws/secured/messenger/user-" + receiver;
            simpMessagingTemplate.convertAndSend(receiverEndPoint,msg);

            String senderEndPoint = "/ws/secured/messenger/user-" + sender;
            simpMessagingTemplate.convertAndSend(senderEndPoint, msg);
        }
        else if (msg.getType() == ChatMessageOneToOneType.IMAGE) {
            // Send image
            String image = msg.getImage();
            msg.setCreatedAt(new Date());
            this.messageService.storeMessage(msg);

            logger.info(String.format("WS-INFO: %s send to %s: %s", sender, receiver, image));

            String receiverEndPoint = "/ws/secured/messenger/user-" + receiver;
            simpMessagingTemplate.convertAndSend(receiverEndPoint, msg);

            String senderEndPoint = "/ws/secured/messenger/user-" + sender;
            simpMessagingTemplate.convertAndSend(senderEndPoint,msg);
        }

    }
    @MessageMapping("/ws/secured/messenger-group")
    public void sendMessageOneToGroup(@Payload ChatMessageOneToGroup msg) {
        if (msg.getType() == ChatMessageOneToGroupType.MESSAGE) {
            Integer member = msg.getMemberId();
            Integer group = msg.getGroupId();
            String message = msg.getMessage();
            msg.setCreatedAt(new Date());

            //this.messageService.storeMessage(msg);

            logger.info(String.format("WS-INFO: %s send to group %s: %s", member, group, message));

            String receiverEndPoint = "/ws/secured/messenger/group-" + group;
            simpMessagingTemplate.convertAndSend(receiverEndPoint, msg);
        }
    }
}
