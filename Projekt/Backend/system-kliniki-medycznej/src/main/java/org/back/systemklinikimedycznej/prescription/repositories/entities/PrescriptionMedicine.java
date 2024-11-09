package org.back.systemklinikimedycznej.prescription.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;

@Builder
@Entity
@Table(name = "prescription_medicine_entry")
@NoArgsConstructor
@AllArgsConstructor
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
