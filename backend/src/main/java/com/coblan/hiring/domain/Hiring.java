package com.coblan.hiring.domain;

import com.coblan.customers.domain.Customer;
import com.coblan.employees.domain.Employee;
import com.coblan.services.domain.Service;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "hirings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hiring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha de inicio del servicio
    @Column(nullable = false)
    private LocalDate startDate;

    // Estado de la contratación
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HiringStatus status;

    private String cancellationReason;
    private LocalDate cancellationdate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // Días y horarios fijos de prestación del servicio
    @ElementCollection
    @CollectionTable(name = "hiring_schedule", joinColumns = @JoinColumn(name = "hiring_id"))
    private Set<ScheduleEntry> schedule = new HashSet<>();

    @Column(nullable = false)
    private Double servicePriceAtHiring;

    // Horas de trabajo generadas
    @OneToMany(mappedBy = "hiring", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkHour> workHours = new ArrayList<>();
}
