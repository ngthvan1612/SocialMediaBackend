package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.GroupMessage;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToOne;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.GroupMessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.MessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.MessageService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.ListUserResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.UserResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.UserServiceImpl;
import com.hcmute.oosd.project.socialmediabackend.domain.base.StorageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.exception.ServiceExceptionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private GroupMessageRepository groupMessageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;

    public MessageServiceImpl() {

    }

    // TODO: Validate with annotation
    // TODO: check fk before create & update
    // TODO: update unique column for delete
    // TODO: swagger
    // TODO: authorize
    // TODO: hash password
    // TODO: loggggggggg

    @Override
    public SuccessfulResponse getListMessageWithAnotherPerson(GetListMessageWithAnotherPersonRequest request) {
        List<Message> rawMessage = this.messageRepository.getAllMessageBetween(request.getUserId(),
                request.getFriendId());

        List<ChatMessageOneToOne> messages = rawMessage.stream().map(msg -> new ChatMessageOneToOne(
                msg.getSender().getId(),
                msg.getReceiver().getId(),
                msg)).toList();

        SuccessfulResponse response = new SuccessfulResponse();
        response.setData(messages);

        return response;
    }

    @Override
    public SuccessfulResponse storeMessage(ChatMessageOneToOne message) {
        Message messageEntity = new Message();
        messageEntity.setContent(message.getMessage());
        messageEntity.setSender(this.entityManager.getReference(User.class, message.getSenderId()));
        messageEntity.setReceiver(this.entityManager.getReference(User.class, message.getReceiverId()));
        messageEntity.setCreatedAt(message.getCreatedAt());

        this.messageRepository.save(messageEntity);

        SuccessfulResponse response = new SuccessfulResponse();
        return response;
    }

    @Override
    public SuccessfulResponse createMessage(CreateMessageRequest request) {
        // Validate

        // Check null

        Optional<User> optionalSender = this.userRepository.findById(request.getSenderId());
        User sender = null;

        if (optionalSender.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại người dùng nào với senderId = " + request.getSenderId());
        } else {
            sender = optionalSender.get();
        }

        Optional<User> optionalReceiver = this.userRepository.findById(request.getReceiverId());
        User receiver = null;

        if (optionalReceiver.isPresent())
            receiver = optionalReceiver.get();

        Optional<GroupMessage> optionalGroup = this.groupMessageRepository.findById(request.getGroupId());
        GroupMessage group = null;

        if (optionalGroup.isPresent())
            group = optionalGroup.get();

        Message message = new Message();

        message.setContent(request.getContent());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setGroup(group);

        // Save to database
        this.messageRepository.save(message);

        // Return
        MessageResponse messageDTO = new MessageResponse(message);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(messageDTO);
        response.addMessage("Tạo Tin nhắn thành công");

        LOG.info("Created message with id = " + message.getId());
        return response;
    }

    @Override
    public GetMessageResponse getMessageById(Integer id) {
        if (!this.messageRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Tin nhắn nào với id là " + id);
        }

        Message message = this.messageRepository.findById(id).get();
        MessageResponse messageDTO = new MessageResponse(message);
        GetMessageResponse response = new GetMessageResponse(messageDTO);

        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public ListMessageResponse searchMessages(Map<String, String> queries) {
        List<MessageResponse> listMessageResponses = this.messageRepository.searchMessage(queries)
                .stream().map(message -> new MessageResponse(message)).toList();

        ListMessageResponse response = new ListMessageResponse(listMessageResponses);
        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public SuccessfulResponse updateMessage(UpdateMessageRequest request) {
        // Check record exists
        if (!this.messageRepository.existsById(request.getMessageId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Tin nhắn nào với id là " + request.getMessageId());
        }

        // Read data from request
        Message message = this.messageRepository.findById(request.getMessageId()).get();

        Optional<User> optionalSender = this.userRepository.findById(request.getSenderId());
        User sender = null;

        if (optionalSender.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Tin nhắn nào với senderId = " + request.getSenderId());
        } else {
            sender = optionalSender.get();
        }

        Optional<User> optionalReceiver = this.userRepository.findById(request.getReceiverId());
        User receiver = null;

        if (optionalReceiver.isPresent()) {
            receiver = optionalReceiver.get();
        }

        Optional<GroupMessage> optionalGroup = this.groupMessageRepository.findById(request.getGroupId());
        GroupMessage group = null;

        if (optionalGroup.isPresent()) {
            group = optionalGroup.get();
        }

        message.setContent(request.getContent());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setGroup(group);

        // Validate unique

        // Update last changed time
        message.setLastUpdatedAt(new Date());

        // Store
        this.messageRepository.save(message);

        // Return
        MessageResponse messageDTO = new MessageResponse(message);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(messageDTO);
        response.addMessage("Cập nhật Tin nhắn thành công");

        LOG.info("Updated message with id = " + message.getId());
        return response;
    }

    @Override
    public SuccessfulResponse deleteMessage(Integer id) {
        if (!this.messageRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Tin nhắn nào với id là " + id);
        }

        Message message = this.messageRepository.findById(id).get();
        message.setDeletedAt(new Date());

        this.messageRepository.save(message);

        SuccessfulResponse response = new SuccessfulResponse();
        response.addMessage("Xóa Tin nhắn thành công");

        LOG.info("Deleted message with id = " + message.getId());
        return response;
    }

    @Override
    public ListMessageResponse getMessageFromOneToOne(Integer usera, Integer userb) {
        if (!this.userRepository.existsById(usera)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Ngừoi dùng nào với id là " + usera);
        }
        if (!this.userRepository.existsById(userb)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Ngừoi dùng nào với id là " + userb);
        }
        List<MessageResponse> listMessageResponses = this.messageRepository.getAllMessageBetween(usera, userb)
                .stream().map(message -> new MessageResponse(message)).toList();

        ListMessageResponse response = new ListMessageResponse(listMessageResponses);
        response.addMessage("Lấy danh sách tin nhắn giữa hai người thành công");

        LOG.info("Get message from userid = " + usera + " and " + userb);
        return response;
    }

    @Override
    public ListUserResponse getAllUserHaveBeenChat(Integer userId) {
        if (!this.userRepository.existsById(userId)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Ngừoi dùng nào với id là " + userId);
        }
        List<UserResponse> listUserResponses = this.messageRepository.getAllUserHaveBeenChat(userId)
                .stream().map(user -> new UserResponse(user)).toList();

        ListUserResponse response = new ListUserResponse(listUserResponses);
        response.addMessage("Lấy toàn bộ người dùng đã nhắn ít nhất 1 tin đối với 1 người thành công");

        LOG.info("Get message from userid = " + userId);
        return response;
    }


}
