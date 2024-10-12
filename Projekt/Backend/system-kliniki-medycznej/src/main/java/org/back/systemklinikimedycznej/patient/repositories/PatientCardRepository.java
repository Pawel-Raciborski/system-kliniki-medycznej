package org.back.systemklinikimedycznej.patient.repositories;

import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PatientCardRepository extends JpaRepository<PatientCard, UUID> {

    @Query("""
    SELECT pt FROM PatientCard pt
    JOIN pt.patient p
    JOIN p.account a
    WHERE a.email = :patientEmail
    """)
    Optional<PatientCard> findCardForPatientWithEmail(@Param("patientEmail") String patientEmail);
}
