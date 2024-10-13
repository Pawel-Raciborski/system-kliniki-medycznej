package org.back.systemklinikimedycznej.patient.repositories;

import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientDetailsRepository extends JpaRepository<PatientDetails,Long> {

    Optional<PatientDetails> findByPatientCard(PatientCard patientCard);

    @Query("""
    SELECT pd FROM PatientDetails as pd
    JOIN pd.patientCard as pc
    JOIN pc.patient as p
    JOIN p.personalDetails as personalDetails
    WHERE personalDetails.pesel = :pesel
    """)
    Optional<PatientDetails> findPatientDetailsByPesel(@Param("pesel") String pesel);
}
