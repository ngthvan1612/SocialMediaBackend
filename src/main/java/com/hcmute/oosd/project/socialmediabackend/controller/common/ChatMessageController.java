package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.SocialMediaBackendApplication;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatMessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SocialMediaBackendApplication.class);
    @MessageMapping("/ws/secured/messenger")
    public void sendMessageOneToOne(@Payload ChatMessageOneToOne msg) {
        Integer sender = msg.getSenderId();
        Integer receiver = msg.getReceiverId();
        String message = msg.getMessage();

        msg.setCreatedAt(new Date());

        logger.info(String.format("WS-INFO: %s send to %s: %s",sender,receiver,message));

        String receiverEndPoint = "/ws/secured/messenger/user-" + receiver;
        simpMessagingTemplate.convertAndSend(receiverEndPoint,msg);

        String senderEndPoint = "/ws/secured/messenger/user-" + sender;
        simpMessagingTemplate.convertAndSend(senderEndPoint,msg);
    }
}
