package com.hcmute.oosd.project.socialmediabackend.controller.admin;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.CreateCommentRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.GetCommentResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.ListCommentResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.comment.UpdateCommentRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.CommentService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    public AdminCommentController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchComment(
            @RequestParam Map<String, String> queries
    ) {
        ListCommentResponse listCommentResponse = this.commentService.searchComments(queries);
        return listCommentResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getComment(
            @PathVariable Integer id
    ) {
        GetCommentResponse getCommentResponse = this.commentService.getCommentById(id);
        return getCommentResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createComment(
            @RequestBody CreateCommentRequest request
    ) {
        SuccessfulResponse createCommentResponse = this.commentService.createComment(request);
        return createCommentResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateComment(
            @PathVariable Integer id,
            @RequestBody UpdateCommentRequest request
    ) {
        request.setCommentId(id);
        SuccessfulResponse updateCommentResponse = this.commentService.updateComment(request);
        return updateCommentResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteComment(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updateCommentResponse = this.commentService.deleteComment(id);
        return updateCommentResponse;
    }
}
