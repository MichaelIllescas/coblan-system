package com.coblan.hiring.usecase;

import com.coblan.hiring.domain.Hiring;
import com.coblan.hiring.domain.WorkHour;

import java.util.List;

public interface GenerateInitialWorkHoursUseCase {
    List<WorkHour> generate(Hiring hiring);
}