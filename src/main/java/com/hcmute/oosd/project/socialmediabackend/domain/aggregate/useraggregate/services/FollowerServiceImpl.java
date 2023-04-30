package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.Follower;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.FollowerRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.services.interfaces.FollowerService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.StorageRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
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
public class FollowerServiceImpl implements FollowerService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StorageRepository storageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public FollowerServiceImpl() {

    }

    // TODO: Validate with annotation
    // TODO: check fk before create & update
    // TODO: update unique column for delete
    // TODO: swagger
    // TODO: authorize
    // TODO: hash password
    // TODO: loggggggggg

    @Override
    public ResponseBaseAbstract createFollower(CreateFollowerRequest request) {
        // Validate

        // Check null

        Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
        User user = null;

        if (optionalUser.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại người dùng nào với userId = " + request.getUserId());
        } else {
            user = optionalUser.get();
        }

        Optional<User> optionalFollow = this.userRepository.findById(request.getFollowId());
        User follow = null;

        if (optionalFollow.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại người dùng nào với followId = " + request.getFollowId());
        } else {
            follow = optionalFollow.get();
        }

        Follower follower = new Follower();

        follower.setUser(user);
        follower.setFollow(follow);

        // Save to database
        this.followerRepository.save(follower);

        // Return
        FollowerResponse followerDTO = new FollowerResponse(follower);

        LOG.info("Created follower with id = " + follower.getId());
        return SuccessResponse.builder()
                .addMessage("Tạo Theo dõi thành công")
                .setData(followerDTO)
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract getFollowerById(Integer id) {
        if (!this.followerRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Theo dõi nào với id là " + id);
        }

        Follower follower = this.followerRepository.findById(id).get();
        FollowerResponse followerDTO = new FollowerResponse(follower);

        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(followerDTO)
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract searchFollowers(Map<String, String> queries) {
        List<FollowerResponse> listFollowerResponses = this.followerRepository.searchFollower(queries)
                .stream().map(follower -> new FollowerResponse(follower)).toList();

        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(listFollowerResponses)
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract updateFollower(UpdateFollowerRequest request) {
        // Check record exists
        if (!this.followerRepository.existsById(request.getFollowerId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Theo dõi nào với id là " + request.getFollowerId());
        }

        // Read data from request
        Follower follower = this.followerRepository.findById(request.getFollowerId()).get();

        Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
        User user = null;

        if (optionalUser.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Theo dõi nào với userId = " + request.getUserId());
        } else {
            user = optionalUser.get();
        }

        Optional<User> optionalFollow = this.userRepository.findById(request.getFollowId());
        User follow = null;

        if (optionalFollow.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Theo dõi nào với followId = " + request.getFollowId());
        } else {
            follow = optionalFollow.get();
        }

        follower.setUser(user);
        follower.setFollow(follow);

        // Validate unique

        // Update last changed time
        follower.setLastUpdatedAt(new Date());

        // Store
        this.followerRepository.save(follower);

        // Return
        FollowerResponse followerDTO = new FollowerResponse(follower);

        LOG.info("Updated follower with id = " + follower.getId());
        return SuccessResponse.builder()
                .addMessage("Cập nhật Theo dõi thành công")
                .setData(followerDTO)
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract deleteFollower(Integer id) {
        if (!this.followerRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Theo dõi nào với id là " + id);
        }

        Follower follower = this.followerRepository.findById(id).get();
        follower.setDeletedAt(new Date());

        this.followerRepository.save(follower);

        LOG.info("Deleted follower with id = " + follower.getId());
        return SuccessResponse.builder()
                .addMessage("Xóa Theo dõi thành công")
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract getFollowerByUserIdAndFollowerId(ToggleFollowerRequest request) {
        Integer userId = request.getUserId();
        Integer followerId = request.getFollowerId();
        if (userId == followerId) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Bạn không được tự follow chính mình");
        }
        if (!this.followerRepository.existsByUserIdAndFollowerId(userId, followerId)) {
            User user = entityManager.getReference(User.class, userId);
            User followed = entityManager.getReference(User.class, followerId);

            Follower follower = new Follower();

            follower.setUser(user);
            follower.setFollow(followed);

            this.followerRepository.save(follower);

            FollowerResponse followerDTO = new FollowerResponse(follower);
            return SuccessResponse.builder()
                    .addMessage("Theo dõi thành công")
                    .setData(followerDTO)
                    .returnUpdated();
        } else {
            Follower follower = this.followerRepository.findByUseridAndFollowerId(userId, followerId).get();
            this.followerRepository.delete(follower);

            FollowerResponse followerDTO = new FollowerResponse(follower);

            return SuccessResponse.builder()
                    .addMessage("Huỷ Theo dõi thành công")
                    .setData(followerDTO)
                    .returnUpdated();
        }

    }

    @Override
    public ResponseBaseAbstract getListPeoplesFollowMe(Integer userid) {
        List<User> userList = this.followerRepository.findListPeoplesFollowMe(userid);

        return SuccessResponse.builder()
                .addMessage("Thành công")
                .setData(userList)
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract getListPeoplesFollowed(Integer followerId) {
        List<User> userList = this.followerRepository.getListPeoplesFollowed(followerId);

        return SuccessResponse.builder()
                .addMessage("Thành công")
                .setData(userList)
                .returnUpdated();
    }

}
