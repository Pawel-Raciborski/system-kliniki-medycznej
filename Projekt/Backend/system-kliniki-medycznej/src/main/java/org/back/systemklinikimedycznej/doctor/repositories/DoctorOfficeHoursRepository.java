package org.back.systemklinikimedycznej.doctor.repositories;

import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface DoctorOfficeHoursRepository extends JpaRepository<DoctorOfficeHours, Long> {

    Optional<DoctorOfficeHours> findByDoctorAndDay(Doctor doctor, DayOfWeek day);

    List<DoctorOfficeHours> findAllByDoctor(Doctor doctor);
}
