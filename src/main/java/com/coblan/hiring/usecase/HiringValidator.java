package com.coblan.hiring.usecase;

import com.coblan.hiring.domain.Hiring;

public interface HiringValidator {
    void validateScheduleConflict(Hiring newHiring);
}