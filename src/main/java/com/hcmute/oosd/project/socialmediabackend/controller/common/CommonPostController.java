package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.CreatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.GetPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.ListPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.UpdatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.PostService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.ReactionService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.GetFollowerResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.ToggleFollowerRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/post")
public class CommonPostController {

    @Autowired
    private PostService postService;
    @Autowired
    private ReactionService reactionService;

    public CommonPostController() {

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
            @RequestBody @Valid CreatePostRequest request,
            @AuthenticationPrincipal User user
    ) {
        request.setAuthorId(user.getId());
        SuccessfulResponse createPostResponse = this.postService.createPost(request);

        return createPostResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updatePost(
            @PathVariable Integer id,
            @RequestBody @Valid UpdatePostRequest request
    ) {
        request.setPostId(id);
        SuccessfulResponse updatePostResponse = this.postService.updatePost(request);
        return updatePostResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deletePost(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updatePostResponse = this.postService.deletePost(id);
        return updatePostResponse;
    }

    @PostMapping("{postId}/like/toggle")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract likePost(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CreateReactionRequest request
)
    {
        SuccessfulResponse likePostReponse = this.reactionService.createReaction(request);
        return likePostReponse;
    }
}
