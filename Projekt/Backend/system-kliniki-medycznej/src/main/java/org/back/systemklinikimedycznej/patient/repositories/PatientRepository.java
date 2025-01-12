package org.back.systemklinikimedycznej.patient.repositories;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPersonalDetailsPesel(String pesel);

    Optional<Patient> findByAccount(Account account);

    @Query(
    """
    SELECT p FROM Patient p
    JOIN p.personalDetails pd
    WHERE pd.pesel like :pesel%
    """)
    List<Patient> findPatientsStartsWithPesel(String pesel);
}
