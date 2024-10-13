package org.back.systemklinikimedycznej.patient.repositories;

import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPersonalDetailsPesel(String pesel);
}
