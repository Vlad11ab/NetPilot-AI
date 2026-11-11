package com.netpilot.api.targets.dtos;

import com.netpilot.api.targets.model.TargetType;


public record TargetResponse(
        Long id,
        String slug,
        String name,
        TargetType type
) {
}
