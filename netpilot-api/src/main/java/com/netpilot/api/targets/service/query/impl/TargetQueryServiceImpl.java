package com.netpilot.api.targets.service.query.impl;

import com.netpilot.api.targets.dtos.TargetResponse;
import com.netpilot.api.targets.exceptions.TargetNotFoundException;
import com.netpilot.api.targets.model.Targets;
import com.netpilot.api.targets.repository.TargetRepository;
import com.netpilot.api.targets.service.query.TargetQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TargetQueryServiceImpl implements TargetQueryService {

    private TargetRepository targetRepository;

    public TargetQueryServiceImpl(TargetRepository targetRepository){
        this.targetRepository=targetRepository;
    }

    @Override
    @Transactional
    public Page<TargetResponse> listTargets(Pageable pageable) {
        return targetRepository.findAll(pageable)
                .map(targets -> new TargetResponse(
                        targets.getId(),
                        targets.getSlug(),
                        targets.getName(),
                        targets.getType()
                ));


    }

    @Override
    @Transactional
    public TargetResponse getTarget(Long id) {
        Targets target = targetRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException());

        return new TargetResponse(
                target.getId(),
                target.getSlug(),
                target.getName(),
                target.getType()
        );
    }
}
