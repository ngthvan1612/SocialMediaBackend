package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Story;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.StoryRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.StoryService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.UserServiceImpl;
import com.hcmute.oosd.project.socialmediabackend.domain.base.StorageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
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
public class StoryServiceImpl implements StoryService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;

    public StoryServiceImpl() {

    }

    //TODO: Validate with annotation
    //TODO: check fk before create & update
    //TODO: update unique column for delete
    //TODO: swagger
    //TODO: authorize
    //TODO: hash password
    //TODO: loggggggggg

    @Override
    public SuccessResponse createStory(CreateStoryRequest request) {
        //Validate


        //Check null

        Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
        User user = null;

        if (optionalUser.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại người dùng nào với userId = " + request.getUserId());
        } else {
            user = optionalUser.get();
        }


        Story story = new Story();

        story.setContent(request.getContent());
        story.setUser(user);

        //Save to database
        this.storyRepository.save(story);

        //Return
        StoryResponse storyDTO = new StoryResponse(story);
        SuccessResponse response = new SuccessResponse();

        response.setData(storyDTO);
        response.addMessage("Tạo Story 24h thành công");

        LOG.info("Created story with id = " + story.getId());
        return response;
    }

    @Override
    public GetStoryResponse getStoryById(Integer id) {
        if (!this.storyRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Story 24h nào với id là " + id);
        }

        Story story = this.storyRepository.findById(id).get();
        StoryResponse storyDTO = new StoryResponse(story);
        GetStoryResponse response = new GetStoryResponse(storyDTO);

        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public ListStoryResponse searchStorys(Map<String, String> queries) {
        List<StoryResponse> listStoryResponses = this.storyRepository.searchStory(queries)
                .stream().map(story -> new StoryResponse(story)).toList();

        ListStoryResponse response = new ListStoryResponse(listStoryResponses);
        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public SuccessResponse updateStory(UpdateStoryRequest request) {
        //Check record exists
        if (!this.storyRepository.existsById(request.getStoryId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Story 24h nào với id là " + request.getStoryId());
        }

        //Read data from request
        Story story = this.storyRepository.findById(request.getStoryId()).get();

        Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
        User user = null;

        if (optionalUser.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Story 24h nào với userId = " + request.getUserId());
        } else {
            user = optionalUser.get();
        }


        story.setContent(request.getContent());
        story.setUser(user);

        //Validate unique


        //Update last changed time
        story.setLastUpdatedAt(new Date());

        //Store
        this.storyRepository.save(story);

        //Return
        StoryResponse storyDTO = new StoryResponse(story);
        SuccessResponse response = new SuccessResponse();

        response.setData(storyDTO);
        response.addMessage("Cập nhật Story 24h thành công");

        LOG.info("Updated story with id = " + story.getId());
        return response;
    }


    @Override
    public SuccessResponse deleteStory(Integer id) {
        if (!this.storyRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Story 24h nào với id là " + id);
        }

        Story story = this.storyRepository.findById(id).get();
        story.setDeletedAt(new Date());

        this.storyRepository.save(story);

        SuccessResponse response = new SuccessResponse();
        response.addMessage("Xóa Story 24h thành công");

        LOG.info("Deleted story with id = " + story.getId());
        return response;
    }

}
  