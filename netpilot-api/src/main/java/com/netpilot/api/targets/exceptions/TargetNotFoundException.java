package com.netpilot.api.targets.exceptions;

import com.netpilot.api.targets.constants.TargetConstants;

public class TargetNotFoundException extends RuntimeException {
    public TargetNotFoundException() {
        super(TargetConstants.TARGET_NOT_FOUND);
    }
}
