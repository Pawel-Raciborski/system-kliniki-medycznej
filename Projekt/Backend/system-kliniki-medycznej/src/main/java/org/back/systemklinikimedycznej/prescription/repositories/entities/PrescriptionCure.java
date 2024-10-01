package org.back.systemklinikimedycznej.prescription.repositories.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription_cure_entry")
public class PrescriptionCure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="dosage")
    private String dosage;
    @Column(name="cure")
    private String cure;
    @ManyToOne
    @JoinColumn(name="prescription_id")
    private Prescription prescription;
}
