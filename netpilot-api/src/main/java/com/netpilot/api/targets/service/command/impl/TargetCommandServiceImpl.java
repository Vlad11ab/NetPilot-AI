package com.netpilot.api.targets.service.command.impl;

import com.netpilot.api.targets.dtos.TargetCreateRequest;
import com.netpilot.api.targets.dtos.TargetResponse;
import com.netpilot.api.targets.model.Targets;
import com.netpilot.api.targets.repository.TargetRepository;
import com.netpilot.api.targets.service.command.TargetCommandService;
import org.springframework.stereotype.Component;

@Component
public class TargetCommandServiceImpl implements TargetCommandService {

    private TargetRepository targetRepository;

    public TargetCommandServiceImpl(TargetRepository targetRepository){
        this.targetRepository=targetRepository;
    }

    @Override
    public TargetResponse createTarget(TargetCreateRequest request) {
        Targets target = Targets.builder()
                .slug(request.slug())
                .name(request.name())
                .type(request.type())
                .build();

        Targets savedTarget = targetRepository.save(target);

        return new TargetResponse(
                savedTarget.getId(),
                savedTarget.getSlug(),
                savedTarget.getName(),
                savedTarget.getType()
        );
    }
}
