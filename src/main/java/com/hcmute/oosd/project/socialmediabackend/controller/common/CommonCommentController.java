package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.CreateCommentRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.GetCommentResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.ListCommentResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.UpdateCommentRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.CommentService;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
// hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/comment")
public class CommonCommentController {

        @Autowired
        private CommentService commentService;

        public CommonCommentController() {

        }

        @GetMapping("")
        @ResponseStatus(HttpStatus.OK)
        public ResponseBaseAbstract searchComment(
                        @RequestParam Map<String, String> queries) {
                ResponseBaseAbstract listCommentResponse = this.commentService.searchComments(queries);
                return listCommentResponse;
        }

        @GetMapping("{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseBaseAbstract getComment(
                        @PathVariable Integer id) {
                ResponseBaseAbstract getCommentResponse = this.commentService.getCommentById(id);
                return getCommentResponse;
        }

        @PostMapping("")
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseBaseAbstract createComment(
                        @RequestBody @Valid CreateCommentRequest request,
                        @AuthenticationPrincipal User user) {
                request.setUserId(user.getId());
                ResponseBaseAbstract createCommentResponse = this.commentService.createComment(request);
                return createCommentResponse;
        }

        @PutMapping("{id}/update")
        @ResponseStatus(HttpStatus.OK)
        public ResponseBaseAbstract updateComment(
                        @PathVariable Integer id,
                        @RequestBody @Valid UpdateCommentRequest request,
                        @AuthenticationPrincipal User user) {
                request.setCommentId(id);
                request.setUserId(user.getId());
                ResponseBaseAbstract updateCommentResponse = this.commentService.updateComment(request);
                return updateCommentResponse;
        }

        @DeleteMapping("{id}/delete")
        @ResponseStatus(HttpStatus.OK)
        public ResponseBaseAbstract deleteComment(
                        @AuthenticationPrincipal User user,
                        @PathVariable Integer id) {
                ResponseBaseAbstract deleteCommentResponse = this.commentService.deleteComment(id, user.getId());
                return deleteCommentResponse;
        }

        @GetMapping("{id}/comments")
        @ResponseStatus(HttpStatus.OK)
        public ResponseBaseAbstract getChildComment(@PathVariable Integer id) {
                ResponseBaseAbstract getChildComment = this.commentService.getByComment(id);
                return getChildComment;
        }

}
