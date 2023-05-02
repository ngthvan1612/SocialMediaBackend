package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Comment;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.CommentRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.PostRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.ReactionRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.CommentService;
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
public class CommentServiceImpl implements CommentService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;

    public CommentServiceImpl() {

    }

    // TODO: Validate with annotation
    // TODO: check fk before create & update
    // TODO: update unique column for delete
    // TODO: swagger
    // TODO: authorize
    // TODO: hash password
    // TODO: loggggggggg

    @Override
    public SuccessResponse createComment(CreateCommentRequest request) {
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

        Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
        Post post = null;

        if (optionalPost.isPresent())
            post = optionalPost.get();

        Optional<Comment> optionalParent = this.commentRepository.findById(request.getParentId());
        Comment parent = null;

        if (optionalParent.isPresent())
            parent = optionalParent.get();

        // Validate comment and post

        if (post == null && parent == null) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Yêu cầu comment phải thuộc post hoặc comment khác");
        }
        if (post != null && parent != null) {
            if (post.getId() != parent.getPost().getId()) {
                throw ServiceExceptionFactory.badRequest()
                        .addMessage("Comment " + parent.getId() + " không thuộc post " + post.getId());
            }
        }
        if (parent != null && parent.getParent() != null) {
            parent = parent.getParent();
        }

        Comment comment = new Comment();

        comment.setContent(request.getContent());
        comment.setUser(user);
        comment.setPost(post);
        comment.setParent(parent);

        // Save to database
        this.commentRepository.save(comment);

        // Return
        CommentResponse commentDTO = new CommentResponse(comment);
        SuccessResponse response = new SuccessResponse();

        response.setData(commentDTO);
        response.addMessage("Tạo Bình luận thành công");

        LOG.info("Created comment with id = " + comment.getId());
        return SuccessResponse.builder()
                .addMessage("Tạo bình luận thành công")
                .setData(new CommentResponse(comment))
                .returnCreated();
    }

    @Override
    public ResponseBaseAbstract getCommentById(Integer id) {
        if (!this.commentRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bình luận nào với id là " + id);
        }

        Comment comment = this.commentRepository.findById(id).get();
        CommentResponse commentDTO = new CommentResponse(comment);
        GetCommentResponse response = new GetCommentResponse(commentDTO);

        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(response)
                .returnGetOK();
    }

    @Override
    public ResponseBaseAbstract searchComments(Map<String, String> queries) {
        List<CommentResponse> listCommentResponses = this.commentRepository.searchComment(queries)
                .stream().map(comment -> new CommentResponse(comment)).toList();

        ListCommentResponse response = new ListCommentResponse(listCommentResponses);
        return SuccessResponse.builder()
                .addMessage("Lấy dữ liệu thành công")
                .setData(response)
                .returnGetOK();
    }

    @Override
    public SuccessResponse updateComment(UpdateCommentRequest request) {
        // Check record exists
        if (!this.commentRepository.existsById(request.getCommentId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bình luận nào với id là " + request.getCommentId());
        }

        // Read data from request
        Comment comment = this.commentRepository.findById(request.getCommentId()).get();

        Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
        User user = null;

        if (optionalUser.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại Bình luận nào với userId = " + request.getUserId());
        } else {
            user = optionalUser.get();
        }

        Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
        Post post = null;

        if (optionalPost.isPresent()) {
            post = optionalPost.get();
        }

        Optional<Comment> optionalParent = this.commentRepository.findById(request.getParentId());
        Comment parent = null;

        if (optionalParent.isPresent()) {
            parent = optionalParent.get();
        }

        comment.setContent(request.getContent());
        comment.setUser(user);
        comment.setPost(post);
        comment.setParent(parent);

        // Validate unique

        // Update last changed time
        comment.setLastUpdatedAt(new Date());

        // Store
        this.commentRepository.save(comment);

        // Return
        CommentResponse commentDTO = new CommentResponse(comment);
        SuccessResponse response = new SuccessResponse();

        response.setData(commentDTO);
        response.addMessage("Cập nhật Bình luận thành công");

        LOG.info("Updated comment with id = " + comment.getId());
        return SuccessResponse.builder()
                .addMessage("Cập nhật bình luận thành công")
                .setData(new CommentResponse(comment))
                .returnUpdated();
    }

    @Override
    public SuccessResponse deleteComment(Integer id, Integer userId) {

        if (!this.commentRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bình luận nào với id là " + id);
        }

        Comment comment = this.commentRepository.findById(id).get();
        if (comment.getUser().getId() != userId && comment.getPost().getAuthor().getId() != userId) {
            throw ServiceExceptionFactory.forbidden()
                    .addMessage("Không có quyền xoá Bình luận với id là " + id);
        }
        Map<String, String> criteria = new HashMap<>();
        criteria.put("parent.id.equal", id.toString());
        List<Comment> comments = commentRepository.searchComment(criteria);
        comments.forEach(item -> {
            item.setDeletedAt(new Date());
        });

        comment.setDeletedAt(new Date());

        this.commentRepository.save(comment);

        SuccessResponse response = new SuccessResponse();
        response.addMessage("Xóa Bình luận thành công");

        LOG.info("Deleted comment with id = " + comment.getId());
        return SuccessResponse.builder()
                .addMessage("Xóa bình luận thành công")
                .returnDeleted();
    }

    @Override
    public SuccessResponse getByPost(Integer id) {
        if (!this.postRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Post nào với id là " + id);
        }
        List<Comment> comments = commentRepository.getByPost(id);
        List<CommentPostResponse> commentPosts = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            commentPosts.add(new CommentPostResponse(comments.get(i).getId(), comments.get(i).getContent(),
                    commentRepository.getByComment(comments.get(i).getId()).size()));
        }

        LOG.info("Get comments from post id = " + id);
        return SuccessResponse.builder()
                .addMessage("Lấy bình luận thuộc post thành công")
                .setData(commentPosts)
                .returnGetOK();
    }

    @Override
    public SuccessResponse getByComment(Integer id) {
        if (!this.commentRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bình luận nào với id là " + id);
        }

        List<CommentPostResponse> commentPosts = new ArrayList<>();
        List<Comment> comments = commentRepository.getByComment(id);
        for (int i = 0; i < comments.size(); i++) {
            commentPosts.add(new CommentPostResponse(comments.get(i).getId(), comments.get(i).getContent(),
                    commentRepository.getByComment(comments.get(i).getId()).size()));
        }
        LOG.info("Get comment with comment id = " + id);

        return SuccessResponse.builder()
                .addMessage("Lấy danh sách Bình luận từ Parent thành công")
                .setData(commentPosts)
                .returnGetOK();
    }

}
