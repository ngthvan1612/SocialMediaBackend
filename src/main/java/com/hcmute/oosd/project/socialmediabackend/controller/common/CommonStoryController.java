package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.CreateStoryRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.GetStoryResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.ListStoryResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.dto.story.UpdateStoryRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.services.interfaces.StoryService;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ResponseBaseAbstract;
import com.hcmute.oosd.project.socialmediabackend.domain.base.SuccessfulResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/story")
public class CommonStoryController {

    @Autowired
    private StoryService storyService;

    public CommonStoryController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchStory(
            @RequestParam Map<String, String> queries
    ) {
        ListStoryResponse listStoryResponse = this.storyService.searchStorys(queries);
        return listStoryResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getStory(
            @PathVariable Integer id
    ) {
        GetStoryResponse getStoryResponse = this.storyService.getStoryById(id);
        return getStoryResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createStory(
            @RequestBody @Valid CreateStoryRequest request
    ) {
        SuccessfulResponse createStoryResponse = this.storyService.createStory(request);
        return createStoryResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateStory(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateStoryRequest request
    ) {
        request.setStoryId(id);
        SuccessfulResponse updateStoryResponse = this.storyService.updateStory(request);
        return updateStoryResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteStory(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updateStoryResponse = this.storyService.deleteStory(id);
        return updateStoryResponse;
    }
}
