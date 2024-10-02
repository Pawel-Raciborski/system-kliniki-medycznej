package org.back.systemklinikimedycznej.doctor.repositories;

import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByPwzNumber(String pwzNumber);
}
