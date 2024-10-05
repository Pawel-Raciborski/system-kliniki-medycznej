package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorFormDto;
import org.back.systemklinikimedycznej.doctor.exceptions.PwzNumberException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.back.systemklinikimedycznej.doctor.validators.DoctorValidator;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorManagementService {
    private final DoctorRepository doctorRepository;
    private final CalendarService calendarService;
    private final DoctorSpecializationsService doctorSpecializationsService;
    public void validatePwzNumber(String pwzNumber) {
        Optional<Doctor> doctorOpt = doctorRepository.findByPwzNumber(pwzNumber);

        DoctorValidator.checkPwzNumberNotExist(doctorOpt.isPresent());
    }

    @Transactional
    public Doctor postDoctorCreateOperations(DoctorFormDto doctorFormDto, Doctor createdDoctor) {
        calendarService.createCalendarForDoctor(createdDoctor);

        List<DoctorSpecialization> doctorSpecializations = doctorSpecializationsService.addSpecializationToDoctor(createdDoctor, doctorFormDto.doctorSpecializations());

        return createdDoctor.withDoctorSpecializations(new HashSet<>(doctorSpecializations));
    }

    public Doctor buildDoctor(DoctorFormDto doctorFormDto, Account createdDoctorAccount, PersonalDetails doctorPersonalDetails) {
        return Doctor.builder()
                .account(createdDoctorAccount)
                .personalDetails(doctorPersonalDetails)
                .description(doctorFormDto.description())
                .pwzNumber(doctorFormDto.pwzNumber())
                .dateOfEmployment(doctorFormDto.dateOfEmployment())
                .build();
    }
}
