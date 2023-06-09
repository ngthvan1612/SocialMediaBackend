package com.hcmute.oosd.project.socialmediabackend.controller.common;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.CreateAnnounceRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.GetAnnounceResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.ListAnnounceResponse;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.dto.announce.UpdateAnnounceRequest;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.services.interfaces.AnnounceService;
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
@RequestMapping("api/common/announce")
public class CommonAnnounceController {

    @Autowired
    private AnnounceService announceService;

    public CommonAnnounceController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchAnnounce(
            @RequestParam Map<String, String> queries
    ) {
        ListAnnounceResponse listAnnounceResponse = this.announceService.searchAnnounces(queries);
        return listAnnounceResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getAnnounce(
            @PathVariable Integer id
    ) {
        GetAnnounceResponse getAnnounceResponse = this.announceService.getAnnounceById(id);
        return getAnnounceResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createAnnounce(
            @RequestBody @Valid CreateAnnounceRequest request
    ) {
        SuccessResponse createAnnounceResponse = this.announceService.createAnnounce(request);
        return createAnnounceResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateAnnounce(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateAnnounceRequest request
    ) {
        request.setAnnounceId(id);
        SuccessResponse updateAnnounceResponse = this.announceService.updateAnnounce(request);
        return updateAnnounceResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteAnnounce(
            @PathVariable Integer id
    ) {
        SuccessResponse updateAnnounceResponse = this.announceService.deleteAnnounce(id);
        return updateAnnounceResponse;
    }
}
