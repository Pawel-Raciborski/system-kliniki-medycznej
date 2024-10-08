package org.back.systemklinikimedycznej.prescription.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;

@Entity
@Table(name = "prescription_medicine_entry")
public class PrescriptionMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="dosage")
    private String dosage;
    @ManyToOne
    @JoinColumn(name="medicine_id")
    private Medicine medicine;
    @ManyToOne
    @JoinColumn(name="prescription_id")
    private Prescription prescription;
}
