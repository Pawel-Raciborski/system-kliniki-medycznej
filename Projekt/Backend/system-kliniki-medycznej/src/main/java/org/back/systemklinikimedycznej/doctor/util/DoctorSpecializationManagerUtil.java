package org.back.systemklinikimedycznej.doctor.util;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorSpecializationDto;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorSpecializationMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class DoctorSpecializationManagerUtil {

    public static Set<DoctorSpecialization> assignDoctorToSpecializations(List<DoctorSpecializationDto> doctorSpecializationDtos, Doctor doctor) {
        Set<DoctorSpecialization> doctorSpecializations = createDoctorSpecializationsSet(doctorSpecializationDtos);
        doctorSpecializations.forEach(doctorSpecialization -> doctorSpecialization.setDoctor(doctor));
        return doctorSpecializations;
    }

    private static Set<DoctorSpecialization> createDoctorSpecializationsSet(List<DoctorSpecializationDto> doctorSpecializationDtos) {
        return doctorSpecializationDtos.stream()
                .map(DoctorSpecializationMapper.INSTANCE::mapFromDto)
                .collect(Collectors.toSet());
    }
}
