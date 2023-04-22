package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.groupmessage.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.GroupMessage;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.UserGroupMessage;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.model.ChatMessageOneToGroup;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.GroupMessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.MessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.UserGroupMessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.GroupMessageService;

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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public SuccessfulResponse createGroupMessage(CreateGroupMessageRequest request) {
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
        SuccessfulResponse response = new SuccessfulResponse();

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
    public SuccessfulResponse updateGroupMessage(UpdateGroupMessageRequest request) {
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
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(groupMessageDTO);
        response.addMessage("Cập nhật Nhóm thành công");

        LOG.info("Updated groupMessage with id = " + groupMessage.getId());
        return response;
    }
    @Override
    public SuccessfulResponse groupstoreMessage(ChatMessageOneToGroup message) {

        GroupMessage group = this.entityManager.getReference(GroupMessage.class, message.getGroupId());
        Message messageEntity = new Message();
        messageEntity.setContent(message.getMessage());
        messageEntity.setSender(this.entityManager.getReference(User.class, message.getMemberId()));
        messageEntity.setCreatedAt(message.getCreatedAt());
        messageEntity.setGroup(group);
        this.messageRepository.save(messageEntity);

        SuccessfulResponse response = new SuccessfulResponse();
        return response;
    }
    @Transactional
    @Override
    public SuccessfulResponse CreateGroup(String groupName, List<Integer> memberIds)
    {
        if (memberIds.size() < 3) {
            throw new RuntimeException("Nhóm phải có ít nhất 3 thành viên");
        }
        List<User> members = userRepository.findAllById(memberIds);

//        UpdateUserRequest request = null;
        GroupMessage groupMessage = new GroupMessage();
//        groupMessage.setId(UUID.randomUUID().toString());
        groupMessage.setDisplayName(groupName);
        groupMessage.setAdmin(members.get(0)); // Chọn admin là thành viên đầu tiên trong danh sách


        // Lưu đối tượng nhóm vào cơ sở dữ liệu
        groupMessageRepository.save(groupMessage);
        // Tạo đối tượng UserGroupMessage cho từng thành viên trong danh sách
        List<UserGroupMessage> userGroupMessages = new ArrayList<>();
        for (User member : members) {
            UserGroupMessage userGroupMessage = new UserGroupMessage();
            userGroupMessage.setGroup(groupMessage);
            userGroupMessage.setMember(member);
            userGroupMessages.add(userGroupMessage);
        }
        // Lưu danh sách UserGroupMessage vào cơ sở dữ liệu
        userGroupMessageRepository.saveAll(userGroupMessages);
        SuccessfulResponse response = new SuccessfulResponse();
        response.addMessage("Tạo nhóm thành công ");
        response.setData(groupMessage);
        return response;

    }
    @Override
    public  SuccessfulResponse AddUserToGroup(Integer groupId, List<Integer> memberIds)
    {
        Optional<GroupMessage> optionalGroupMessage = groupMessageRepository.findById(groupId);
        List<User> members = userRepository.findAllById(memberIds);
        List<UserGroupMessage> userGroupMessages = new ArrayList<>();
        GroupMessage groupMessage = optionalGroupMessage.get();
        for (User member : members) {
            UserGroupMessage userGroupMessage = new UserGroupMessage();
            userGroupMessage.setGroup(groupMessage);
            userGroupMessage.setMember(member);
            userGroupMessages.add(userGroupMessage);
            }
        userGroupMessageRepository.saveAll(userGroupMessages);
        SuccessfulResponse response = new SuccessfulResponse();
        response.addMessage("Thêm thành viên vào nhóm thành công");
        response.setData(groupMessage);
        return response;
    }
    @Override
    public SuccessfulResponse deleteGroupMessage(Integer id) {
        if (!this.groupMessageRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Nhóm nào với id là " + id);
        }

        GroupMessage groupMessage = this.groupMessageRepository.findById(id).get();
        groupMessage.setDeletedAt(new Date());

        this.groupMessageRepository.save(groupMessage);

        SuccessfulResponse response = new SuccessfulResponse();
        response.addMessage("Xóa Nhóm thành công");

        LOG.info("Deleted groupMessage with id = " + groupMessage.getId());
        return response;
    }

}
  