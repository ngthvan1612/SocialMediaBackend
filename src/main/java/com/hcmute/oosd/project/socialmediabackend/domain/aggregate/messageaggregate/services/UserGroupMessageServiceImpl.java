package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.dto.usergroupmessage.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.GroupMessage;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.UserGroupMessage;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.GroupMessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories.UserGroupMessageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.services.interfaces.UserGroupMessageService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.UserServiceImpl;
import com.hcmute.oosd.project.socialmediabackend.domain.base.StorageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.exception.ServiceExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserGroupMessageServiceImpl implements UserGroupMessageService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserGroupMessageRepository userGroupMessageRepository;

    @Autowired
    private GroupMessageRepository groupMessageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;

    public UserGroupMessageServiceImpl() {

    }

    //TODO: Validate with annotation
    //TODO: check fk before create & update
    //TODO: update unique column for delete
    //TODO: swagger
    //TODO: authorize
    //TODO: hash password
    //TODO: loggggggggg

    @Override
    public SuccessfulResponse createUserGroupMessage(CreateUserGroupMessageRequest request) {
        //Validate


        //Check null

        Optional<GroupMessage> optionalGroup = this.groupMessageRepository.findById(request.getGroupId());
        GroupMessage group = null;

        if (optionalGroup.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Nhóm nào với groupId = " + request.getGroupId());
        } else {
            group = optionalGroup.get();
        }


        Optional<User> optionalMember = this.userRepository.findById(request.getMemberId());
        User member = null;

        if (optionalMember.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại người dùng nào với memberId = " + request.getMemberId());
        } else {
            member = optionalMember.get();
        }


        UserGroupMessage userGroupMessage = new UserGroupMessage();

        userGroupMessage.setGroup(group);
        userGroupMessage.setMember(member);

        //Save to database
        this.userGroupMessageRepository.save(userGroupMessage);

        //Return
        UserGroupMessageResponse userGroupMessageDTO = new UserGroupMessageResponse(userGroupMessage);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(userGroupMessageDTO);
        response.addMessage("Tạo Chi tiết nhóm thành công");

        LOG.info("Created userGroupMessage with id = " + userGroupMessage.getId());
        return response;
    }

    @Override
    public GetUserGroupMessageResponse getUserGroupMessageById(Integer id) {
        if (!this.userGroupMessageRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Chi tiết nhóm nào với id là " + id);
        }

        UserGroupMessage userGroupMessage = this.userGroupMessageRepository.findById(id).get();
        UserGroupMessageResponse userGroupMessageDTO = new UserGroupMessageResponse(userGroupMessage);
        GetUserGroupMessageResponse response = new GetUserGroupMessageResponse(userGroupMessageDTO);

        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public ListUserGroupMessageResponse searchUserGroupMessages(Map<String, String> queries) {
        List<UserGroupMessageResponse> listUserGroupMessageResponses = this.userGroupMessageRepository.searchUserGroupMessage(queries)
                .stream().map(userGroupMessage -> new UserGroupMessageResponse(userGroupMessage)).toList();

        ListUserGroupMessageResponse response = new ListUserGroupMessageResponse(listUserGroupMessageResponses);
        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public SuccessfulResponse updateUserGroupMessage(UpdateUserGroupMessageRequest request) {
        //Check record exists
        if (!this.userGroupMessageRepository.existsById(request.getUserGroupMessageId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Chi tiết nhóm nào với id là " + request.getUserGroupMessageId());
        }

        //Read data from request
        UserGroupMessage userGroupMessage = this.userGroupMessageRepository.findById(request.getUserGroupMessageId()).get();

        Optional<GroupMessage> optionalGroup = this.groupMessageRepository.findById(request.getGroupId());
        GroupMessage group = null;

        if (optionalGroup.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Chi tiết nhóm nào với groupId = " + request.getGroupId());
        } else {
            group = optionalGroup.get();
        }


        Optional<User> optionalMember = this.userRepository.findById(request.getMemberId());
        User member = null;

        if (optionalMember.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Chi tiết nhóm nào với memberId = " + request.getMemberId());
        } else {
            member = optionalMember.get();
        }


        userGroupMessage.setGroup(group);
        userGroupMessage.setMember(member);

        //Validate unique


        //Update last changed time
        userGroupMessage.setLastUpdatedAt(new Date());

        //Store
        this.userGroupMessageRepository.save(userGroupMessage);

        //Return
        UserGroupMessageResponse userGroupMessageDTO = new UserGroupMessageResponse(userGroupMessage);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(userGroupMessageDTO);
        response.addMessage("Cập nhật Chi tiết nhóm thành công");

        LOG.info("Updated userGroupMessage with id = " + userGroupMessage.getId());
        return response;
    }


    @Override
    public SuccessfulResponse deleteUserGroupMessage(Integer id) {
        if (!this.userGroupMessageRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Chi tiết nhóm nào với id là " + id);
        }

        UserGroupMessage userGroupMessage = this.userGroupMessageRepository.findById(id).get();
        userGroupMessage.setDeletedAt(new Date());

        this.userGroupMessageRepository.save(userGroupMessage);

        SuccessfulResponse response = new SuccessfulResponse();
        response.addMessage("Xóa Chi tiết nhóm thành công");

        LOG.info("Deleted userGroupMessage with id = " + userGroupMessage.getId());
        return response;
    }

}
  