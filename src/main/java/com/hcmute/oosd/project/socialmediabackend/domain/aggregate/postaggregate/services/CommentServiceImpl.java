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

    //TODO: Validate with annotation
    //TODO: check fk before create & update
    //TODO: update unique column for delete
    //TODO: swagger
    //TODO: authorize
    //TODO: hash password
    //TODO: loggggggggg

    @Override
    public SuccessfulResponse createComment(CreateCommentRequest request) {
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

        if (optionalPost.isPresent())
            post = optionalPost.get();


        Optional<Comment> optionalParent = this.commentRepository.findById(request.getParentId());
        Comment parent = null;

        if (optionalParent.isPresent())
            parent = optionalParent.get();

        // Validate comment and post

        if (post== null && parent== null){
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Yêu cầu comment phải thuộc post hoặc comment khác" );
        }
        if (post!= null && parent != null){
            if(post.getId() != parent.getPost().getId()){
                throw ServiceExceptionFactory.badRequest()
                        .addMessage("Comment "+parent.getId()+" không thuộc post "+ post.getId() );
            }
        }

        // comment level 2

        if (parent.getParent()!= null){
            parent = parent.getParent();
        }


        Comment comment = new Comment();

        comment.setContent(request.getContent());
        comment.setUser(user);
        comment.setPost(post);
        comment.setParent(parent);

        //Save to database
        this.commentRepository.save(comment);

        //Return
        CommentResponse commentDTO = new CommentResponse(comment);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(commentDTO);
        response.addMessage("Tạo Bình luận thành công");

        LOG.info("Created comment with id = " + comment.getId());
        return response;
    }

    @Override
    public GetCommentResponse getCommentById(Integer id) {
        if (!this.commentRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bình luận nào với id là " + id);
        }

        Comment comment = this.commentRepository.findById(id).get();
        CommentResponse commentDTO = new CommentResponse(comment);
        GetCommentResponse response = new GetCommentResponse(commentDTO);

        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public ListCommentResponse searchComments(Map<String, String> queries) {
        List<CommentResponse> listCommentResponses = this.commentRepository.searchComment(queries)
                .stream().map(comment -> new CommentResponse(comment)).toList();

        ListCommentResponse response = new ListCommentResponse(listCommentResponses);
        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public SuccessfulResponse updateComment(UpdateCommentRequest request) {
        //Check record exists
        if (!this.commentRepository.existsById(request.getCommentId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bình luận nào với id là " + request.getCommentId());
        }

        //Read data from request
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

        //Validate unique


        //Update last changed time
        comment.setLastUpdatedAt(new Date());

        //Store
        this.commentRepository.save(comment);

        //Return
        CommentResponse commentDTO = new CommentResponse(comment);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(commentDTO);
        response.addMessage("Cập nhật Bình luận thành công");

        LOG.info("Updated comment with id = " + comment.getId());
        return response;
    }


    @Override
    public SuccessfulResponse deleteComment(Integer id, Integer userId ) {

        if (!this.commentRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bình luận nào với id là " + id);
        }

        Comment comment = this.commentRepository.findById(id).get();
        if (comment.getUser().getId() != userId &&  comment.getPost().getAuthor().getId() != userId ) {
            throw ServiceExceptionFactory.forbidden()
                    .addMessage("Không có quyền xoá Bình luận với id là " + id);
        }
        comment.setDeletedAt(new Date());

        this.commentRepository.save(comment);



        SuccessfulResponse response = new SuccessfulResponse();
        response.addMessage("Xóa Bình luận thành công");

        LOG.info("Deleted comment with id = " + comment.getId());
        return response;
    }

}
  