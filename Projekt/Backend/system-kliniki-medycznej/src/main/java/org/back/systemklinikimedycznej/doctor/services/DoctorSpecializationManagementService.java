package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorSpecializationDto;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorSpecializationMapper;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorSpecializationRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.back.systemklinikimedycznej.doctor.validators.DoctorSpecializationValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorSpecializationManagementService {
    private final DoctorSpecializationRepository doctorSpecializationRepository;

    public void checkSpecializationsExistForDoctor(Doctor doctor, List<DoctorSpecializationDto> doctorSpecializations) {
        for (DoctorSpecializationDto doctorSpecializationDto : doctorSpecializations) {
            String specializationName = doctorSpecializationDto.name();
            DoctorSpecializationValidator.checkDoctorHasNotSpecialization(
                    doctorSpecializationRepository.findByDoctorAndName(doctor, specializationName).isPresent(),
                    specializationName);

        }
    }

    public Set<DoctorSpecialization> assignDoctorToSpecializations(List<DoctorSpecializationDto> doctorSpecializationDtos, Doctor doctor) {
        Set<DoctorSpecialization> doctorSpecializations = createDoctorSpecializationsSet(doctorSpecializationDtos);
        doctorSpecializations.forEach(doctorSpecialization -> doctorSpecialization.setDoctor(doctor));
        return doctorSpecializations;
    }

    private Set<DoctorSpecialization> createDoctorSpecializationsSet(List<DoctorSpecializationDto> doctorSpecializationDtos) {
        return doctorSpecializationDtos.stream()
                .map(DoctorSpecializationMapper.INSTANCE::mapFromDto)
                .collect(Collectors.toSet());
    }
}
