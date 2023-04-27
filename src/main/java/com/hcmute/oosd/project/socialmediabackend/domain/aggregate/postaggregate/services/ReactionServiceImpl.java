package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Reaction;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.PostRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.ReactionRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.ReactionService;
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
public class ReactionServiceImpl implements ReactionService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;

    public ReactionServiceImpl() {

    }

    //TODO: Validate with annotation
    //TODO: check fk before create & update
    //TODO: update unique column for delete
    //TODO: swagger
    //TODO: authorize
    //TODO: hash password
    //TODO: loggggggggg

    @Override
    public SuccessResponse createReaction(CreateReactionRequest request) {
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


        Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
        Post post = null;

        if (optionalPost.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Bài đăng nào với postId = " + request.getPostId());
        } else {
            post = optionalPost.get();
        }


        Reaction reaction = new Reaction();

        reaction.setReaction(request.getReaction());
        reaction.setUser(user);
        reaction.setPost(post);

        //Save to database
        this.reactionRepository.save(reaction);

        //Return
        ReactionResponse reactionDTO = new ReactionResponse(reaction);
        SuccessResponse response = new SuccessResponse();

        response.setData(reactionDTO);
        response.addMessage("Tạo Thả cảm xúc thành công");

        LOG.info("Created reaction with id = " + reaction.getId());
        return response;
    }

    @Override
    public GetReactionResponse getReactionById(Integer id) {
        if (!this.reactionRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Thả cảm xúc nào với id là " + id);
        }

        Reaction reaction = this.reactionRepository.findById(id).get();
        ReactionResponse reactionDTO = new ReactionResponse(reaction);
        GetReactionResponse response = new GetReactionResponse(reactionDTO);

        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public ListReactionResponse searchReactions(Map<String, String> queries) {
        List<ReactionResponse> listReactionResponses = this.reactionRepository.searchReaction(queries)
                .stream().map(reaction -> new ReactionResponse(reaction)).toList();

        ListReactionResponse response = new ListReactionResponse(listReactionResponses);
        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public SuccessResponse updateReaction(UpdateReactionRequest request) {
        //Check record exists
        if (!this.reactionRepository.existsById(request.getReactionId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Thả cảm xúc nào với id là " + request.getReactionId());
        }

        //Read data from request
        Reaction reaction = this.reactionRepository.findById(request.getReactionId()).get();

        Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
        User user = null;

        if (optionalUser.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Thả cảm xúc nào với userId = " + request.getUserId());
        } else {
            user = optionalUser.get();
        }


        Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
        Post post = null;

        if (optionalPost.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Thả cảm xúc nào với postId = " + request.getPostId());
        } else {
            post = optionalPost.get();
        }


        reaction.setReaction(request.getReaction());
        reaction.setUser(user);
        reaction.setPost(post);

        //Validate unique


        //Update last changed time
        reaction.setLastUpdatedAt(new Date());

        //Store
        this.reactionRepository.save(reaction);

        //Return
        ReactionResponse reactionDTO = new ReactionResponse(reaction);
        SuccessResponse response = new SuccessResponse();

        response.setData(reactionDTO);
        response.addMessage("Cập nhật Thả cảm xúc thành công");

        LOG.info("Updated reaction with id = " + reaction.getId());
        return response;
    }


    @Override
    public SuccessResponse deleteReaction(Integer id) {
        if (!this.reactionRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Thả cảm xúc nào với id là " + id);
        }

        Reaction reaction = this.reactionRepository.findById(id).get();
        reaction.setDeletedAt(new Date());

        this.reactionRepository.save(reaction);

        SuccessResponse response = new SuccessResponse();
        response.addMessage("Xóa Thả cảm xúc thành công");

        LOG.info("Deleted reaction with id = " + reaction.getId());
        return response;
    }

}
  