package com.netpilot.api.targets.controller;

import com.netpilot.api.targets.dtos.TargetCreateRequest;
import com.netpilot.api.targets.dtos.TargetResponse;
import com.netpilot.api.targets.service.command.TargetCommandService;
import com.netpilot.api.targets.service.query.TargetQueryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/targets")
public class TargetController {

    private final TargetCommandService targetCommandService;
    private TargetQueryService targetQueryService;

    public TargetController(TargetQueryService targetQueryService, TargetCommandService targetCommandService){
        this.targetQueryService=targetQueryService;
        this.targetCommandService = targetCommandService;
    }

    @GetMapping
    public ResponseEntity<Page<TargetResponse>> listTargets(Pageable pageable) {
        log.info("HTTP METHOD GET listTargets /api/v1/targets");
        return ResponseEntity.ok(targetQueryService.listTargets(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TargetResponse> getTarget(@PathVariable Long id){
        log.info("HTTP METHOD GET getTarget /api/v1/targets/{}", id);
        return ResponseEntity.ok(targetQueryService.getTarget(id));
    }

    @PostMapping
    public ResponseEntity<TargetResponse> createTarget(@Valid @RequestBody TargetCreateRequest request){
        log.info("HTTP METHOD POST createTarget /api/v1/targets/");
        return ResponseEntity.status(HttpStatus.CREATED).body(targetCommandService.createTarget(request));
    }
}
