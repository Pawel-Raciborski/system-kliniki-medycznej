package org.back.systemklinikimedycznej.doctor.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.OfficeHoursDto;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorOfficeHoursException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorOfficeHoursRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfficeHoursValidator {
    private final DoctorOfficeHoursRepository doctorOfficeHoursRepository;
    public void validateDayNotExist(Doctor doctorToAddOfficeHours, OfficeHoursDto officeHoursDto) {
        Optional<DoctorOfficeHours> existOfficeHoursOpt = doctorOfficeHoursRepository.findByDoctorAndDay(doctorToAddOfficeHours, officeHoursDto.day());

        checkDayNotExist(existOfficeHoursOpt.isPresent());
    }

    private void checkDayNotExist(boolean existDay) {
        if(existDay){
            throw new DoctorOfficeHoursException("Podany dzień jest już zarezerwowany dla podanego lekarza!", HttpStatus.CONFLICT);
        }
    }
}
