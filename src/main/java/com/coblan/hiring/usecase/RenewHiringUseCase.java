package com.coblan.hiring.usecase;

import com.coblan.hiring.domain.Hiring;

public interface RenewHiringUseCase {
    Hiring renew(Hiring previousHiring);
}
