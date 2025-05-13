package com.coblan.hiring.domain;

import com.coblan.employees.domain.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    // Relación con Hiring
    @ManyToOne(optional = false)
    @JoinColumn(name = "hiring_id", nullable = false)
    private Hiring hiring;

    // Empleado asignado a la hora (puede diferir del original si fue reasignado)
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime hour;

    @Column(nullable = false)
    private Integer duration; // en minutos

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkHourStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkHourType type;

    private LocalDate confirmedDate;
    private LocalTime confirmedHour;

    @Column(length = 500)
    private String note;

    @Column(nullable = false)
    private double price;
}
