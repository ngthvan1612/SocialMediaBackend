package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.CreatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.GetPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.ListPostResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.post.UpdatePostRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.enums.ReactionType;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.CommentService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.PostService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.ReactionService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.GetFollowerResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.dto.follower.ToggleFollowerRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/post")
@Slf4j
public class CommonPostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

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

    @GetMapping("/me/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchMyPost(
            @AuthenticationPrincipal User user
    ) {
        Map<String, String> queries = new HashMap<>();
        queries.put("author.id.equal", user.getId().toString());
        ListPostResponse listPostResponse = this.postService.searchPosts(queries);
        return listPostResponse;
    }
    @GetMapping("/{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchUserPost(
            @PathVariable Integer userId
    ) {
        Map<String, String> queries = new HashMap<>();
        queries.put("author.id.equal", userId.toString());
        queries.put("privacy.equal", "PUBLIC");
        //TODO: thêm một số criteria về tài khoản kiểm tra người xem và người được xem có thỏa các điều kiên không
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
            @RequestBody @Valid UpdatePostRequest request,
            @AuthenticationPrincipal User user
    ) {
        request.setAuthorId(user.getId());
        request.setPostId(id);
        SuccessfulResponse updatePostResponse = this.postService.updatePost(request);
        return updatePostResponse;
    }


    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deletePost(
            @PathVariable Integer id
    ) {
        //TODO: Kiem tra người xóa có phải chủ post hay khong
        SuccessfulResponse updatePostResponse = this.postService.deletePost(id);
        return updatePostResponse;
    }

    @GetMapping("{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getComments(
            @PathVariable Integer id
    ) {
        SuccessfulResponse getCommentsResponse = commentService.getByPost(id);
        return getCommentsResponse;
    }

    @GetMapping("{postId}/like/toggle")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract likePost(
            @AuthenticationPrincipal User user,
            @PathVariable Integer postId
    )
    {
        CreateReactionRequest request = new CreateReactionRequest(ReactionType.LIKE,user.getId(),postId);
        SuccessfulResponse likePostReponse = this.postService.toogleLikePost(request);
        return likePostReponse;
    }
}
