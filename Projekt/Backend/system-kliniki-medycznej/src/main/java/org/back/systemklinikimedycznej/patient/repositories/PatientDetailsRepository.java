package org.back.systemklinikimedycznej.patient.repositories;

import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientDetailsRepository extends JpaRepository<PatientDetails,Long> {

    Optional<PatientDetails> findByPatientCard(PatientCard patientCard);

}
