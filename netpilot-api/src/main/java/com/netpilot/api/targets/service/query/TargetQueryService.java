package com.netpilot.api.targets.service.query;

import com.netpilot.api.targets.dtos.TargetResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TargetQueryService {
    Page<TargetResponse> listTargets(Pageable pageable);
    TargetResponse getTarget(Long targetId);
}
