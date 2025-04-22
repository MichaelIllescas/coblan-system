package com.coblan.services.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "work_hours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkHourStatus status;

    @Column(nullable = false)
    private double price;

    @Column(length = 500)
    private String note;
}
