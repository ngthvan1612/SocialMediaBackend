package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.controller.common.CommonCommentController;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.services.interfaces.AnnounceService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Comment;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Reaction;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.UserTagFriendPost;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.model.postcontent.PostContentBase;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.model.postcontent.PostContentFactory;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.CommentRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.PostRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.ReactionRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.UserTagFriendPostRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.PostService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.ReactionService;
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

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserTagFriendPostRepository userTagFriendPostRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private AnnounceService announceService;

    @Autowired
    private ReactionService reactionService;

    @Autowired
    private CommentRepository commentRepository;

    public PostServiceImpl() {

    }

    @Override
    public ResponseBaseAbstract createPost(CreatePostRequest request) {
        // Validate
        PostContentBase postContent = PostContentFactory.fromJson(request.getContent());

        assert postContent != null;
        postContent.validate(true);

        // Check null

        Optional<User> optionalAuthor = this.userRepository.findById(request.getAuthorId());
        User author = null;

        if (optionalAuthor.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại người dùng nào với authorId = " + request.getAuthorId());
        } else {
            author = optionalAuthor.get();
        }

        if (request.getTags() != null) {
            for (Integer userId : request.getTags()) {
                if (userId == request.getAuthorId()) {
                    throw ServiceExceptionFactory.badRequest()
                            .addMessage("Người được gắn thẻ chung không được là người đăng");
                }
                if (!userRepository.existsById(userId)) {
                    throw ServiceExceptionFactory.badRequest()
                            .addMessage("Không tồn tại người dùng nào với id là " + userId);
                }
            }
        }

        Post post = new Post();

        post.setContent(request.getContent());
        post.setPrivacy(request.getPrivacy());
        post.setAuthor(author);

        // Save to database
        this.postRepository.save(post);
        if (request.getTags() != null) {
            List<User> userList = userRepository.findAllById(request.getTags());
            this.userTagFriendPostRepository.saveAll(
                    userList.stream().map(user -> new UserTagFriendPost(user, post)).toList());
        }

        // Return
        LOG.info("Created post with id = " + post.getId());

        // Announce
        announceService.onCreatedNewPost(post.getId());

        return SuccessResponse.builder()
                .addMessage("Tạo bài đăng thành công")
                .setData(new PostResponse(post))
                .returnCreated();
    }

    public AdditionalData4Post GetAdditionalData4Post(Integer id, Integer uid) {
        Map<String, String> queries = new HashMap<>();
        queries.put("post.id.equal", id.toString());
        // reaction
        List<Reaction> reactions = this.reactionRepository.searchReaction(queries);

        // comment
        List<Comment> comments = this.commentRepository.searchComment(queries);

        AdditionalData4Post data = new AdditionalData4Post(
                reactions.size(),
                reactions.stream().anyMatch(
                        reaction -> reaction.getUser().getId().equals(uid) && reaction.getPost().getId().equals(id)),
                comments.size());

        return data;
    }

    @Override
    public ResponseBaseAbstract getPostById(Integer id, Integer uid) {
        if (!this.postRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bài đăng nào với id là " + id);
        }

        Post post = this.postRepository.findById(id).get();

        AdditionalData4Post data = GetAdditionalData4Post(id, uid);
        System.out.println("debug");
        LOG.info(data.getTotalReact() + " " + data.getTotalComment() + " " + data.getIsReacted());

        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(new PostResponse(post, GetAdditionalData4Post(id, uid)))
                .returnGetOK();
    }

    @Override
    public ResponseBaseAbstract searchPosts(Map<String, String> queries, Integer uid) {
        List<PostResponse> listPostResponses = this.postRepository.searchPost(queries)
                .stream().map(post -> new PostResponse(post, GetAdditionalData4Post(post.getId(), uid))).toList();

        ListPostResponse response = new ListPostResponse(listPostResponses);

        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(response)
                .returnGetOK();
    }

    @Override
    public ResponseBaseAbstract updatePost(UpdatePostRequest request) {
        // Check record exists
        if (!this.postRepository.existsById(request.getPostId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bài đăng nào với id là " + request.getPostId());
        }

        // Read data from request
        Post post = this.postRepository.findById(request.getPostId()).get();

        Optional<User> optionalAuthor = this.userRepository.findById(request.getAuthorId());
        User author = null;

        if (optionalAuthor.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Bài đăng nào với authorId = " + request.getAuthorId());
        } else {
            author = optionalAuthor.get();
        }

        post.setContent(request.getContent());
        post.setPrivacy(request.getPrivacy());
        post.setAuthor(author);

        // Validate unique

        // Update last changed time
        post.setLastUpdatedAt(new Date());

        // Store
        this.postRepository.save(post);
        if (request.getTags() != null) {
            List<User> userList = userRepository.findAllById(request.getTags());
            this.userTagFriendPostRepository.saveAll(
                    userList.stream().map(user -> new UserTagFriendPost(user, post)).toList());
        }
        // Return
        LOG.info("Updated post with id = " + post.getId());

        return SuccessResponse.builder()
                .addMessage("Cập nhật bài đăng thành công")
                .setData(new PostResponse(post))
                .returnUpdated();
    }

    @Override
    public ResponseBaseAbstract deletePost(Integer id) {
        if (!this.postRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bài đăng nào với id là " + id);
        }

        Post post = this.postRepository.findById(id).get();
        post.setDeletedAt(new Date());
c        this.postRepository.save(post);

        reactionRepository.deleteByPostId(post.getId());

        LOG.info("Deleted post with id = " + post.getId());

        return SuccessResponse.builder()
                .addMessage("Xóa bài đăng thành công")
                .returnDeleted();
    }

    @Override
    public SuccessResponse toogleLikePost(CreateReactionRequest request) {
        if (!this.postRepository.existsById(request.getPostId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bài đăng nào với id là " + request.getPostId());
        }
        if (!this.userRepository.existsById(request.getUserId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Ngừoi dùng nào với id là " + request.getUserId());
        }
        Optional<Reaction> optionalReaction = reactionRepository.findByPostAndUser(request.getUserId(),
                request.getPostId());
        Reaction reaction = null;

        if (!optionalReaction.isEmpty()) {
            reaction = optionalReaction.get();
            reactionRepository.delete(reaction);
        } else {
            reactionService.createReaction(request);
        }
        SuccessResponse response = new SuccessResponse();
        response.addMessage("Like/Dislike post thành công");

        LOG.info("Toggle like post with id = " + request.getPostId());
        return response;
    }

}
