package com.hcmute.oosd.project.socialmediabackend.controller.admin;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.CreatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.GetPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.ListPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.UpdatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.PostService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/post")
public class AdminPostController {

    @Autowired
    private PostService postService;

    public AdminPostController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchPost(
            @RequestParam Map<String, String> queries
    ) {
        ListPostResponse listPostResponse = this.postService.searchPosts(queries);
        return listPostResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getPost(
            @PathVariable Integer id
    ) {
        GetPostResponse getPostResponse = this.postService.getPostById(id);
        return getPostResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createPost(
            @RequestBody CreatePostRequest request,
            @AuthenticationPrincipal User user
    ) {
        request.setAuthorId(user.getId());
        SuccessResponse createPostResponse = this.postService.createPost(request);
        return createPostResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updatePost(
            @PathVariable Integer id,
            @RequestBody UpdatePostRequest request,
            @AuthenticationPrincipal User user
    ) {
        request.setAuthorId(user.getId());
        request.setPostId(id);
        SuccessResponse updatePostResponse = this.postService.updatePost(request);
        return updatePostResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deletePost(
            @PathVariable Integer id
    ) {
        SuccessResponse updatePostResponse = this.postService.deletePost(id);
        return updatePostResponse;
    }
}
