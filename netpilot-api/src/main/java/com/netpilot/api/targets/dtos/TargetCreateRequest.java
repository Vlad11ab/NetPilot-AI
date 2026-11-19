package com.netpilot.api.targets.dtos;

import com.netpilot.api.targets.model.TargetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TargetCreateRequest(

        @NotBlank(message = "Slug is mandatory")
        @Size(min = 3, max = 50, message = "Slug must be between 3 and 50 characters")
        @Pattern(regexp = "^[a-z0-9-]+$", message = "Invalid Format")
        String slug,

        @NotBlank(message = "Name is mandatory")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,

        @NotNull(message = "Type is mandatory")
        TargetType type
){}
