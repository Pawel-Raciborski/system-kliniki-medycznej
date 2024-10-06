package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorSpecializationDto;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorSpecializationNotFoundException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorSpecializationRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.back.systemklinikimedycznej.doctor.util.DoctorSpecializationManagerUtil;
import org.back.systemklinikimedycznej.doctor.validators.DoctorSpecializationValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorSpecializationsService {
    private final DoctorSpecializationRepository doctorSpecializationRepository;
    private final DoctorSpecializationValidator doctorSpecializationValidator;

    @Transactional
    public List<DoctorSpecialization> addSpecializationToDoctor(
            Doctor doctor,
            List<DoctorSpecializationDto> doctorSpecializationDtos) {
        doctorSpecializationValidator.checkSpecializationsExistForDoctor(doctor, doctorSpecializationDtos);

        Set<DoctorSpecialization> doctorSpecializations = DoctorSpecializationManagerUtil.assignDoctorToSpecializations(doctorSpecializationDtos, doctor);
        return doctorSpecializationRepository.saveAll(doctorSpecializations);
    }

    public List<DoctorSpecialization> findAllSpecializationsForDoctor(Doctor doctor) {
        return doctorSpecializationRepository.findAllByDoctor(doctor);
    }

    @Transactional
    public DoctorSpecialization removeDoctorSpecialization(Doctor doctor, String specializationName) {
        DoctorSpecialization doctorSpecializationToRemove = findByDoctorAndName(doctor, specializationName);

        doctorSpecializationRepository.delete(doctorSpecializationToRemove);
        return doctorSpecializationToRemove;
    }

    private DoctorSpecialization findByDoctorAndName(Doctor doctor, String specializationName) {
        return doctorSpecializationRepository.findByDoctorAndName(doctor, specializationName)
                .orElseThrow(() -> new DoctorSpecializationNotFoundException("Nie znaleziono specjalizacji o nazwie [%s] dla podanego doktora".formatted(specializationName), HttpStatus.NOT_FOUND));
    }
}
