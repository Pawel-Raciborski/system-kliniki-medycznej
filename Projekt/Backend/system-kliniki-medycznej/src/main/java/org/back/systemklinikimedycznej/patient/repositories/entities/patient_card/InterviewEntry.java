package org.back.systemklinikimedycznej.patient.repositories.entities.patient_card;

import jakarta.persistence.*;

@Entity
@Table(name = "interview_entry")
public class InterviewEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="question")
    private String question;
    @Column(name="patient_answer")
    private String patientAnswer;
    @ManyToOne
    @JoinColumn(name="interview_id")
    private Interview interview;
}
