package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.*;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories.PostRepository;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.PostService;
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
public class PostServiceImpl implements PostService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;

    public PostServiceImpl() {

    }

    //TODO: Validate with annotation
    //TODO: check fk before create & update
    //TODO: update unique column for delete
    //TODO: swagger
    //TODO: authorize
    //TODO: hash password
    //TODO: loggggggggg

    @Override
    public SuccessfulResponse createPost(CreatePostRequest request) {
        //Validate


        //Check null

        Optional<User> optionalAuthor = this.userRepository.findById(request.getAuthorId());
        User author = null;

        if (optionalAuthor.isEmpty()) {
            throw ServiceExceptionFactory.badRequest()
                    .addMessage("Không tồn tại người dùng nào với authorId = " + request.getAuthorId());
        } else {
            author = optionalAuthor.get();
        }


        Post post = new Post();

        post.setContent(request.getContent());
        post.setPrivacy(request.getPrivacy());
        post.setAuthor(author);

        //Save to database
        this.postRepository.save(post);

        //Return
        PostResponse postDTO = new PostResponse(post);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(postDTO);
        response.addMessage("Tạo Bài đăng thành công");

        LOG.info("Created post with id = " + post.getId());
        return response;
    }

    @Override
    public GetPostResponse getPostById(Integer id) {
        if (!this.postRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bài đăng nào với id là " + id);
        }

        Post post = this.postRepository.findById(id).get();
        PostResponse postDTO = new PostResponse(post);
        GetPostResponse response = new GetPostResponse(postDTO);

        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public ListPostResponse searchPosts(Map<String, String> queries) {
        List<PostResponse> listPostResponses = this.postRepository.searchPost(queries)
                .stream().map(post -> new PostResponse(post)).toList();

        ListPostResponse response = new ListPostResponse(listPostResponses);
        response.addMessage("Lấy dữ liệu thành công");

        return response;
    }


    @Override
    public SuccessfulResponse updatePost(UpdatePostRequest request) {
        //Check record exists
        if (!this.postRepository.existsById(request.getPostId())) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bài đăng nào với id là " + request.getPostId());
        }

        //Read data from request
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

        //Validate unique


        //Update last changed time
        post.setLastUpdatedAt(new Date());

        //Store
        this.postRepository.save(post);

        //Return
        PostResponse postDTO = new PostResponse(post);
        SuccessfulResponse response = new SuccessfulResponse();

        response.setData(postDTO);
        response.addMessage("Cập nhật Bài đăng thành công");

        LOG.info("Updated post with id = " + post.getId());
        return response;
    }


    @Override
    public SuccessfulResponse deletePost(Integer id) {
        if (!this.postRepository.existsById(id)) {
            throw ServiceExceptionFactory.notFound()
                    .addMessage("Không tìm thấy Bài đăng nào với id là " + id);
        }

        Post post = this.postRepository.findById(id).get();
        post.setDeletedAt(new Date());

        this.postRepository.save(post);

        SuccessfulResponse response = new SuccessfulResponse();
        response.addMessage("Xóa Bài đăng thành công");

        LOG.info("Deleted post with id = " + post.getId());
        return response;
    }



}
  