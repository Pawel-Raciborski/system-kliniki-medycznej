package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorFormDto;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorNotExistException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.personal_details.services.PersonalDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorManagementService doctorManagementService;
    private final AccountService accountService;
    private final PersonalDetailsService personalDetailsService;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Doctor create(DoctorFormDto doctorFormDto) {
        doctorManagementService.validatePwzNumber(doctorFormDto.pwzNumber());

        Account createdDoctorAccount = accountService.create(doctorFormDto.registerAccountData());
        PersonalDetails doctorPersonalDetails = personalDetailsService.create(doctorFormDto.personalDetails());

        Doctor doctorToCreate = doctorManagementService.buildDoctor(doctorFormDto, createdDoctorAccount, doctorPersonalDetails);
        Doctor createdDoctor = saveDoctor(doctorToCreate);

        return doctorManagementService.postDoctorCreateOperations(doctorFormDto, createdDoctor);
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
}
