package com.netpilot.api.targets.controller;

import com.netpilot.api.targets.dtos.TargetResponse;
import com.netpilot.api.targets.service.command.TargetCommandService;
import com.netpilot.api.targets.service.query.TargetQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        return ResponseEntity.ok(targetQueryService.listTargets(pageable));
    }
}
