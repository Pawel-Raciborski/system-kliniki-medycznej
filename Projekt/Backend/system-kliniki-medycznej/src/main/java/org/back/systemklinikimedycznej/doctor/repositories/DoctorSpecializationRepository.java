package org.back.systemklinikimedycznej.doctor.repositories;

import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, Long> {
    Optional<DoctorSpecialization> findByName(String specializationName);
    Optional<DoctorSpecialization> findByDoctorAndName(Doctor doctor, String specializationName);
    List<DoctorSpecialization> findAllByDoctor(Doctor doctor);

    @Query("""
    select d FROM DoctorSpecialization as ds
    JOIN ds.doctor d
    WHERE ds.name = :specializationName
    """)
    Page<Doctor> findPagedDoctorsWithSpecialization(@Param("specializationName") String specializationName, Pageable pageable);


    @Query(
            """
            SELECT DISTINCT d.name FROM DoctorSpecialization d
            """
    )
    List<String> getAllSpecializationNames();

}
