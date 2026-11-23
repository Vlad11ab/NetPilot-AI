package com.netpilot.api.targets.exceptions;

import com.netpilot.api.common.error.NotFoundException;
import com.netpilot.api.targets.constants.TargetConstants;

public class TargetNotFoundException extends NotFoundException {
    public TargetNotFoundException() {
        super(TargetConstants.TARGET_NOT_FOUND);
    }
}
