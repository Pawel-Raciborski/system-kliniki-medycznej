package org.back.systemklinikimedycznej.doctor.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@Table(name = "doctor_specialization")
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSpecialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "realized_date")
    private LocalDate realizedDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
