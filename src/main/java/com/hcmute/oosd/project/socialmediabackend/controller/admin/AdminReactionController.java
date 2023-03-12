package com.hcmute.oosd.project.socialmediabackend.controller.admin;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.GetReactionResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.ListReactionResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.UpdateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.ReactionService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/reaction")
public class AdminReactionController {

    @Autowired
    private ReactionService reactionService;

    public AdminReactionController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchReaction(
            @RequestParam Map<String, String> queries
    ) {
        ListReactionResponse listReactionResponse = this.reactionService.searchReactions(queries);
        return listReactionResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getReaction(
            @PathVariable Integer id
    ) {
        GetReactionResponse getReactionResponse = this.reactionService.getReactionById(id);
        return getReactionResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createReaction(
            @RequestBody CreateReactionRequest request
    ) {
        SuccessfulResponse createReactionResponse = this.reactionService.createReaction(request);
        return createReactionResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateReaction(
            @PathVariable Integer id,
            @RequestBody UpdateReactionRequest request
    ) {
        request.setReactionId(id);
        SuccessfulResponse updateReactionResponse = this.reactionService.updateReaction(request);
        return updateReactionResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteReaction(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updateReactionResponse = this.reactionService.deleteReaction(id);
        return updateReactionResponse;
    }
}
