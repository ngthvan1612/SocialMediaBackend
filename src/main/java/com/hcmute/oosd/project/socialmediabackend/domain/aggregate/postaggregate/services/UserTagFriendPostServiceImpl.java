package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.usertagfriendpost.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.UserTagFriendPost;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.PostRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.UserTagFriendPostRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.UserTagFriendPostService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.UserServiceImpl;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
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
public class UserTagFriendPostServiceImpl implements UserTagFriendPostService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserTagFriendPostRepository userTagFriendPostRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;

    public UserTagFriendPostServiceImpl() {

    }

    //TODO: Validate with annotation
    //TODO: check fk before create & update
    //TODO: update unique column for delete
    //TODO: swagger
    //TODO: authorize
    //TODO: hash password
    //TODO: loggggggggg

    @Override
    public ResponseBaseAbstract createUserTagFriendPost(CreateUserTagFriendPostRequest request) {
        //Validate


        //Check null

        Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
        Post post = null;

        if (optionalPost.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Bài đăng nào với postId = " + request.getPostId());
        } else {
            post = optionalPost.get();
        }


        Optional<User> optionalFriend = this.userRepository.findById(request.getFriendId());
        User friend = null;

        if (optionalFriend.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại người dùng nào với friendId = " + request.getFriendId());
        } else {
            friend = optionalFriend.get();
        }


        UserTagFriendPost userTagFriendPost = new UserTagFriendPost();

        userTagFriendPost.setPost(post);
        userTagFriendPost.setFriend(friend);

        //Save to database
        this.userTagFriendPostRepository.save(userTagFriendPost);

        //Return
        UserTagFriendPostResponse userTagFriendPostDTO = new UserTagFriendPostResponse(userTagFriendPost);
        SuccessResponse response = new SuccessResponse();

        response.setData(userTagFriendPostDTO);
        response.addMessage("Tạo Tag bạn bè của bài đăng thành công");

        LOG.info("Created userTagFriendPost with id = " + userTagFriendPost.getId());
        return SuccessResponse.builder()
                .addMessage("Tạo bài đăng thành công")
                .setData(new UserTagFriendPostResponse(userTagFriendPost))
                .returnCreated();
    }

    @Override
    public ResponseBaseAbstract getUserTagFriendPostById(Integer id) {
        if (!this.userTagFriendPostRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Tag bạn bè của bài đăng nào với id là " + id);
        }

        UserTagFriendPost userTagFriendPost = this.userTagFriendPostRepository.findById(id).get();
        UserTagFriendPostResponse userTagFriendPostDTO = new UserTagFriendPostResponse(userTagFriendPost);
        GetUserTagFriendPostResponse response = new GetUserTagFriendPostResponse(userTagFriendPostDTO);

        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(response)
                .returnGetOK();
    }

    @Override
    public ResponseBaseAbstract searchUserTagFriendPosts(Map<String, String> queries) {
        List<UserTagFriendPostResponse> listUserTagFriendPostResponses = this.userTagFriendPostRepository.searchUserTagFriendPost(queries)
                .stream().map(userTagFriendPost -> new UserTagFriendPostResponse(userTagFriendPost)).toList();

        ListUserTagFriendPostResponse response = new ListUserTagFriendPostResponse(listUserTagFriendPostResponses);
        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(response)
                .returnGetOK();
    }

    @Override
    public ResponseBaseAbstract updateUserTagFriendPost(UpdateUserTagFriendPostRequest request) {
        //Check record exists
        if (!this.userTagFriendPostRepository.existsById(request.getUserTagFriendPostId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Tag bạn bè của bài đăng nào với id là " + request.getUserTagFriendPostId());
        }

        //Read data from request
        UserTagFriendPost userTagFriendPost = this.userTagFriendPostRepository.findById(request.getUserTagFriendPostId()).get();

        Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
        Post post = null;

        if (optionalPost.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Tag bạn bè của bài đăng nào với postId = " + request.getPostId());
        } else {
            post = optionalPost.get();
        }


        Optional<User> optionalFriend = this.userRepository.findById(request.getFriendId());
        User friend = null;

        if (optionalFriend.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Tag bạn bè của bài đăng nào với friendId = " + request.getFriendId());
        } else {
            friend = optionalFriend.get();
        }


        userTagFriendPost.setPost(post);
        userTagFriendPost.setFriend(friend);

        //Validate unique


        //Update last changed time
        userTagFriendPost.setLastUpdatedAt(new Date());

        //Store
        this.userTagFriendPostRepository.save(userTagFriendPost);

        //Return
        UserTagFriendPostResponse userTagFriendPostDTO = new UserTagFriendPostResponse(userTagFriendPost);
        SuccessResponse response = new SuccessResponse();
        LOG.info("Updated userTagFriendPost with id = " + userTagFriendPost.getId());
        return SuccessResponse.builder()
                .addMessage("Cập nhật bài đăng thành công")
                .setData(new UserTagFriendPostResponse(userTagFriendPost))
                .returnUpdated();
    }


    @Override
    public ResponseBaseAbstract deleteUserTagFriendPost(Integer id) {
        if (!this.userTagFriendPostRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Tag bạn bè của bài đăng nào với id là " + id);
        }

        UserTagFriendPost userTagFriendPost = this.userTagFriendPostRepository.findById(id).get();
        userTagFriendPost.setDeletedAt(new Date());

        this.userTagFriendPostRepository.save(userTagFriendPost);
        LOG.info("Deleted userTagFriendPost with id = " + userTagFriendPost.getId());
        return SuccessResponse.builder()
                .addMessage("Xóa Tag bạn bè của bài đăng thành công")
                .returnDeleted();
    }

}
  