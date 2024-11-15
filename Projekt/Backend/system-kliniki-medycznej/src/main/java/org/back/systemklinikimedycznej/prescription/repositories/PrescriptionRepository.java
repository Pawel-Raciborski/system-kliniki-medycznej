package org.back.systemklinikimedycznej.prescription.repositories;

import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {
    @Query("""
            SELECT count(p) FROM Prescription p
            WHERE p.patient = :patient
            AND p.expirationDate >= CURRENT_DATE
            """)
    Long countPatientPrescriptions(@Param("patient") Patient patient);
}
