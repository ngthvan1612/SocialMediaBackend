package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.GroupMessageResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.message.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.GroupMessage;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToOne;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.GroupMessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.MessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.MessageService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.types.ChatMessageOneToOneType;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.ListUserResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.user.UserResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.UserServiceImpl;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.StorageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.exception.ServiceExceptionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.json.JSONObject;
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
    public ResponseBaseAbstract getListMessageWithAnotherPerson(GetListMessageWithAnotherPersonRequest request) {
        List<Message> rawMessage = this.messageRepository.getAllMessageBetween(request.getUserId(),
                request.getFriendId());

        List<ChatMessageOneToOne> messages = rawMessage.stream().map(msg -> new ChatMessageOneToOne(
                msg.getSender().getId(),
                msg.getReceiver().getId(),
                msg)).toList();
        return SuccessResponse.builder()
                .setData( messages )
                .returnGetOK();
    }

    @Override
    public SuccessResponse storeMessage(ChatMessageOneToOne message) {
        Message messageEntity = new Message();
        messageEntity.setSender(this.entityManager.getReference(User.class, message.getSenderId()));
        messageEntity.setReceiver(this.entityManager.getReference(User.class, message.getReceiverId()));
        messageEntity.setCreatedAt(message.getCreatedAt());
        messageEntity.setIsRead(false);

        if (message.getType() == ChatMessageOneToOneType.MESSAGE)
        {
            messageEntity.setContent(message.getMessage());
            messageEntity.setLastUpdatedAt(new Date());
        } else if (message.getType() == ChatMessageOneToOneType.IMAGE) {
            messageEntity.setContent(message.getImage());
        }

        messageRepository.save(messageEntity);
        if (message.getType() == ChatMessageOneToOneType.SEEN) {
            Integer messageId = message.getMessageId();
//            Integer receiverId = message.getReceiverId();
            Message seenMessage = this.messageRepository.findById(messageId).orElse(null);
            if (seenMessage != null) {
                seenMessage.setIsRead(true); // Đánh dấu tin nhắn đã đọc
                seenMessage.setLastUpdatedAt(new Date()); // Cập nhật thời gian cập nhật cuối cùng của tin nhắn
                this.messageRepository.save(seenMessage); // Lưu tin nhắn đã cập nhật vào cơ sở dữ liệu
            }
        }
        SuccessResponse response = new SuccessResponse();
        return response;
    }

    @Override
    public ResponseBaseAbstract createMessage(CreateMessageRequest request) {
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
        message.setIsRead(false);

        // Save to database
        this.messageRepository.save(message);

        LOG.info("Created message with id = " + message.getId());
        return SuccessResponse.builder()
                .addMessage("Tạo Tin nhắn thành công")
                .setData(new MessageResponse(message))
                .returnCreated();
    }

    @Override
    public ResponseBaseAbstract getMessageById(Integer id) {
        if (!this.messageRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Tin nhắn nào với id là " + id);
        }

        Message message = this.messageRepository.findById(id).get();
        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(new MessageResponse(message))
                .returnGetOK();
    }

    @Override
    public ResponseBaseAbstract searchMessages(Map<String, String> queries) {
        List<MessageResponse> listMessageResponses = this.messageRepository.searchMessage(queries)
                .stream().map(message -> new MessageResponse(message)).toList();

        ListMessageResponse response = new ListMessageResponse(listMessageResponses);

        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(response)
                .returnGetOK();
    }

    @Override
    public ResponseBaseAbstract updateMessage(UpdateMessageRequest request) {
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

        LOG.info("Updated message with id = " + message.getId());
        return SuccessResponse.builder()
                .addMessage("Cập nhật Tin nhắn thành công")
                .setData(new MessageResponse(message))
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract deleteMessage(Integer id) {
        if (!this.messageRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Tin nhắn nào với id là " + id);
        }

        Message message = this.messageRepository.findById(id).get();
        message.setDeletedAt(new Date());

        this.messageRepository.save(message);

        LOG.info("Deleted message with id = " + message.getId());
        return SuccessResponse.builder()
                .addMessage("Xóa Tin nhắn thành công")
                .returnDeleted();
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
