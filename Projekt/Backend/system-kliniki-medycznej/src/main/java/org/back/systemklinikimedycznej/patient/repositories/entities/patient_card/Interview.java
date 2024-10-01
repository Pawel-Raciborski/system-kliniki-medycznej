package org.back.systemklinikimedycznej.patient.repositories.entities.patient_card;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;
    @OneToMany(mappedBy = "interview")
    private Set<InterviewEntry> interviewEntries;

    @OneToOne(mappedBy = "interview")
    private PatientCard patientCard;
}
