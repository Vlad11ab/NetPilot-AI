package com.netpilot.api.targets.service.command;

import com.netpilot.api.targets.dtos.TargetCreateRequest;
import com.netpilot.api.targets.dtos.TargetResponse;

public interface TargetCommandService {
    TargetResponse createTarget(TargetCreateRequest request);
    


}
