package com.coblan.services.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "monthly_hours", nullable = false)
    private Integer monthlyHours;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(length = 500)
    private String note;

}
