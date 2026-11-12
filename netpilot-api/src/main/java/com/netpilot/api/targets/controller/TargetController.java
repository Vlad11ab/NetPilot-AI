package com.netpilot.api.targets.controller;

import com.netpilot.api.targets.dtos.TargetResponse;
import com.netpilot.api.targets.service.command.TargetCommandService;
import com.netpilot.api.targets.service.query.TargetQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/targets")
public class TargetController {

    private TargetQueryService targetQueryService;

    public TargetController( TargetQueryService targetQueryService){
        this.targetQueryService=targetQueryService;
    }

    @GetMapping
    public ResponseEntity<Page<TargetResponse>> listTargets(Pageable pageable) {
        log.info("HTTP METHOD GET listTargets /api/v1/targets");
        return ResponseEntity.ok(targetQueryService.listTargets(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TargetResponse> getTarget(@PathVariable Long targetId){
        log.info("HTTP METHOD GET getTarget /api/v1/targets/{}", targetId);

    }
}
