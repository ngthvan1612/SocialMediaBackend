package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.GroupMessage;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToGroup;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.GroupMessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.MessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.UserGroupMessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.GroupMessageService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.UserServiceImpl;
import com.hcmute.oosd.project.socialmediabackend.domain.base.StorageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.exception.ServiceExceptionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private GroupMessageRepository groupMessageRepository;
    private UserGroupMessageRepository userGroupMessageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;

    public GroupMessageServiceImpl() {

    }

    //TODO: Validate with annotation
    //TODO: check fk before create & update
    //TODO: update unique column for delete
    //TODO: swagger
    //TODO: authorize
    //TODO: hash password
    //TODO: loggggggggg

    @Override
    public SuccessResponse createGroupMessage(CreateGroupMessageRequest request) {
        //Validate


        //Check null

        Optional<User> optionalAdmin = this.userRepository.findById(request.getAdminId());
        User admin = null;

        if (optionalAdmin.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại người dùng nào với adminId = " + request.getAdminId());
        } else {
            admin = optionalAdmin.get();
        }


        GroupMessage groupMessage = new GroupMessage();

        groupMessage.setDisplayName(request.getDisplayName());
        groupMessage.setAdmin(admin);

        //Save to database
        this.groupMessageRepository.save(groupMessage);

        //Return
        GroupMessageResponse groupMessageDTO = new GroupMessageResponse(groupMessage);
        SuccessResponse response = new SuccessResponse();

        response.setData(groupMessageDTO);
        response.addMessage("Tạo Nhóm thành công");

        LOG.info("Created groupMessage with id = " + groupMessage.getId());
        return response;
    }

    @Override
    public GetGroupMessageResponse getGroupMessageById(Integer id) {
        if (!this.groupMessageRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Nhóm nào với id là " + id);
        }

        GroupMessage groupMessage = this.groupMessageRepository.findById(id).get();
        GroupMessageResponse groupMessageDTO = new GroupMessageResponse(groupMessage);
        GetGroupMessageResponse response = new GetGroupMessageResponse(groupMessageDTO);

        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public ListGroupMessageResponse searchGroupMessages(Map<String, String> queries) {
        List<GroupMessageResponse> listGroupMessageResponses = this.groupMessageRepository.searchGroupMessage(queries)
                .stream().map(groupMessage -> new GroupMessageResponse(groupMessage)).toList();

        ListGroupMessageResponse response = new ListGroupMessageResponse(listGroupMessageResponses);
        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public SuccessResponse updateGroupMessage(UpdateGroupMessageRequest request) {
        //Check record exists
        if (!this.groupMessageRepository.existsById(request.getGroupMessageId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Nhóm nào với id là " + request.getGroupMessageId());
        }

        //Read data from request
        GroupMessage groupMessage = this.groupMessageRepository.findById(request.getGroupMessageId()).get();

        Optional<User> optionalAdmin = this.userRepository.findById(request.getAdminId());
        User admin = null;

        if (optionalAdmin.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Nhóm nào với adminId = " + request.getAdminId());
        } else {
            admin = optionalAdmin.get();
        }


        groupMessage.setDisplayName(request.getDisplayName());
        groupMessage.setAdmin(admin);

        //Validate unique


        //Update last changed time
        groupMessage.setLastUpdatedAt(new Date());

        //Store
        this.groupMessageRepository.save(groupMessage);

        //Return
        GroupMessageResponse groupMessageDTO = new GroupMessageResponse(groupMessage);
        SuccessResponse response = new SuccessResponse();

        response.setData(groupMessageDTO);
        response.addMessage("Cập nhật Nhóm thành công");

        LOG.info("Updated groupMessage with id = " + groupMessage.getId());
        return response;
    }
    @Override
    public SuccessResponse groupstoreMessage(ChatMessageOneToGroup message) {

        GroupMessage group = this.entityManager.getReference(GroupMessage.class, message.getGroupId());
        Message messageEntity = new Message();
        messageEntity.setContent(message.getMessage());
        messageEntity.setSender(this.entityManager.getReference(User.class, message.getMemberId()));
        messageEntity.setCreatedAt(message.getCreatedAt());
        messageEntity.setGroup(group);

        this.messageRepository.save(messageEntity);

        SuccessResponse response = new SuccessResponse();
        return response;
    }

    @Override
    public SuccessResponse deleteGroupMessage(Integer id) {
        if (!this.groupMessageRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Nhóm nào với id là " + id);
        }

        GroupMessage groupMessage = this.groupMessageRepository.findById(id).get();
        groupMessage.setDeletedAt(new Date());

        this.groupMessageRepository.save(groupMessage);

        SuccessResponse response = new SuccessResponse();
        response.addMessage("Xóa Nhóm thành công");

        LOG.info("Deleted groupMessage with id = " + groupMessage.getId());
        return response;
    }

}
  