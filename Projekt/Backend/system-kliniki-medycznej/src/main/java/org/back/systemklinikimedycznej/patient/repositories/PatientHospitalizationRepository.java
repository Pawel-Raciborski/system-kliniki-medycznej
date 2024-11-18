package org.back.systemklinikimedycznej.patient.repositories;

import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientHospitalizationRepository extends JpaRepository<Hospitalization,Long> {
    @Query("""
    SELECT h FROM Hospitalization as h
    WHERE h.patientDisease = :patientDisease
    ORDER BY h.medicineUpdateDate DESC LIMIT 1
    """)
    Hospitalization findCurrentHospitalization(PatientDisease patientDisease);

    @Query("""
    SELECT h FROM Hospitalization h
    WHERE h.patientDisease = :patientDisease
    ORDER BY h.medicineUpdateDate DESC
    """)
    Page<Hospitalization> findHospitalizations(PatientDisease patientDisease, Pageable pageable);
}
