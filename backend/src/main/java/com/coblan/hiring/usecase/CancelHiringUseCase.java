package com.coblan.hiring.usecase;


public interface CancelHiringUseCase {
    void execute(Long hiringId, String cancellationReason);
}