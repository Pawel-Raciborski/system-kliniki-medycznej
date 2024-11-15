package org.back.systemklinikimedycznej.patient.repositories;

import org.back.systemklinikimedycznej.patient.domain.CureStatus;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientDiseaseRepository extends JpaRepository<PatientDisease,Long> {
    @Query("""
    SELECT count(pd) FROM PatientDisease pd
    WHERE pd.patientCard = :patientCard
    AND pd.finishCureDate IS NULL
    """)
    Long countPatientDiseases(@Param("patientCard") PatientCard patientCard);

    @Query("""
    SELECT pd FROM PatientDisease pd
    WHERE pd.patientCard = :patientCard
    AND pd.cureStatus IN :cureStatusList
    """)
    Page<PatientDisease> findByPatientCardAndWithCureStatusPaged(PatientCard patientCard, List<CureStatus> cureStatusList, PageRequest pageRequest);
}
