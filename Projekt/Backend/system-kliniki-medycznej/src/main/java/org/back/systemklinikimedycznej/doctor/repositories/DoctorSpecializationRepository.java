package org.back.systemklinikimedycznej.doctor.repositories;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, Long> {
    Optional<DoctorSpecialization> findByName(String specializationName);
    Optional<DoctorSpecialization> findByDoctorAndName(Doctor doctor, String name);
    List<DoctorSpecialization> findAllByDoctor(Doctor doctor);
}
