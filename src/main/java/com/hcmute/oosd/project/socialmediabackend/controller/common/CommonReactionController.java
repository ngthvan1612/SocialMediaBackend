package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.GetReactionResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.ListReactionResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.reaction.UpdateReactionRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.ReactionService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/reaction")
public class CommonReactionController {

    @Autowired
    private ReactionService reactionService;

    public CommonReactionController() {

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
            @RequestBody @Valid CreateReactionRequest request
    ) {
        SuccessResponse createReactionResponse = this.reactionService.createReaction(request);
        return createReactionResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateReaction(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateReactionRequest request
    ) {
        request.setReactionId(id);
        SuccessResponse updateReactionResponse = this.reactionService.updateReaction(request);
        return updateReactionResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteReaction(
            @PathVariable Integer id
    ) {
        SuccessResponse updateReactionResponse = this.reactionService.deleteReaction(id);
        return updateReactionResponse;
    }
}
