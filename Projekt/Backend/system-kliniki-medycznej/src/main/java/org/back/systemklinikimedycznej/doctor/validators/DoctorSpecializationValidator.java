package org.back.systemklinikimedycznej.doctor.validators;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorSpecializationDto;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorSpecializationExistException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorSpecializationRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorSpecializationValidator {
    private final DoctorSpecializationRepository doctorSpecializationRepository;

    public void checkSpecializationsExistForDoctor(Doctor doctor, List<DoctorSpecializationDto> doctorSpecializations) {
        for (DoctorSpecializationDto doctorSpecializationDto : doctorSpecializations) {
            String specializationName = doctorSpecializationDto.name();
            checkDoctorHasNotSpecialization(doctorSpecializationRepository.findByDoctorAndName(doctor, specializationName).isPresent(), specializationName);

        }
    }
    private void checkDoctorHasNotSpecialization(boolean hasSpecialization, String specializationName){
        if(hasSpecialization){
            throw new DoctorSpecializationExistException("Lekarz posiada już specjalizację o nazwie [%s]!".formatted(specializationName), HttpStatus.CONFLICT);
        }
    }

}
