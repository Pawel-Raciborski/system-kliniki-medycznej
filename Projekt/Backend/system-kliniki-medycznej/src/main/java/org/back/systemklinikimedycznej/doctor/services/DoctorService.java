package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorFormDto;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorNotExistException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.back.systemklinikimedycznej.doctor.util.DoctorManagerUtil;
import org.back.systemklinikimedycznej.doctor.validators.DoctorValidator;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.personal_details.services.PersonalDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorValidator doctorValidator;
    private final AccountService accountService;
    private final PersonalDetailsService personalDetailsService;
    private final DoctorRepository doctorRepository;
    private final CalendarService calendarService;
    private final DoctorSpecializationsService doctorSpecializationsService;

    @Transactional
    public Doctor create(DoctorFormDto doctorFormDto) {
        doctorValidator.validatePwzNumber(doctorFormDto.pwzNumber());

        Account createdDoctorAccount = accountService.create(doctorFormDto.registerAccountData());
        PersonalDetails doctorPersonalDetails = personalDetailsService.create(doctorFormDto.personalDetails());

        Doctor doctorToCreate = DoctorManagerUtil.buildDoctor(doctorFormDto, createdDoctorAccount, doctorPersonalDetails);
        Doctor createdDoctor = saveDoctor(doctorToCreate);

        return postDoctorCreateOperations(doctorFormDto, createdDoctor);
    }

    @Transactional
    public String updatePwzNumber(String oldPwzNumber, String newPwzNumber) {
        Doctor doctorToUpdate = findByPwzNumber(oldPwzNumber);
        doctorToUpdate.setPwzNumber(newPwzNumber);

        return saveDoctor(doctorToUpdate).getPwzNumber();
    }

    @Transactional
    public void delete(String pwzNumber) {
        Doctor doctorToRemove = findByPwzNumber(pwzNumber);

        doctorRepository.deleteById(doctorToRemove.getId());
    }

    public Doctor findByPwzNumber(String pwzNumber) {
        return doctorRepository.findByPwzNumber(pwzNumber).orElseThrow(
                () -> new DoctorNotExistException("Doktor z numerem PWZ: %s nie istnieje".formatted(pwzNumber), HttpStatus.NOT_FOUND)
        );
    }

    private Doctor saveDoctor(Doctor doctorToCreate) {
        return doctorRepository.save(doctorToCreate);
    }

    public Doctor postDoctorCreateOperations(DoctorFormDto doctorFormDto, Doctor createdDoctor) {
        calendarService.createCalendarForDoctor(createdDoctor);

        List<DoctorSpecialization> doctorSpecializations = doctorSpecializationsService.addSpecializationToDoctor(createdDoctor, doctorFormDto.doctorSpecializations());

        return createdDoctor.withDoctorSpecializations(new HashSet<>(doctorSpecializations));
    }
}
