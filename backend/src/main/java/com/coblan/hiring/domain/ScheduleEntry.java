package com.coblan.hiring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleEntry implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "week_day", nullable = false)
    private DayOfWeek day;

    @Column(name = "hour", nullable = false)
    private LocalTime hour;
}
