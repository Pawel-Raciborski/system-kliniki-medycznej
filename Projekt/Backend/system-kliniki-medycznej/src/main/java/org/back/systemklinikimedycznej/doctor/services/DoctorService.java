package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorFormDto;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorNotExistException;
import org.back.systemklinikimedycznej.doctor.exceptions.PwzNumberException;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorSpecializationMapper;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.personal_details.services.PersonalDetailsService;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final AccountService accountService;
    private final PersonalDetailsService personalDetailsService;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Doctor create(DoctorFormDto doctorFormDto) {
        Optional<Doctor> doctorOpt = doctorRepository.findByPwzNumber(doctorFormDto.pwzNumber());

        if (doctorOpt.isPresent()) {
            throw new PwzNumberException("Podany numer pwz już zajęty!", HttpStatus.CONFLICT);
        }

        Account createdDoctorAccount = accountService.create(doctorFormDto.registerAccountData());
        PersonalDetails doctorPersonalDetails = personalDetailsService.create(doctorFormDto.personalDetails());
        Set<DoctorSpecialization> doctorSpecializations = doctorFormDto.doctorSpecializations().stream()
                .map(DoctorSpecializationMapper.INSTANCE::mapFromDto).collect(Collectors.toSet());

        Doctor doctorToCreate = buildDoctor(doctorFormDto, createdDoctorAccount, doctorPersonalDetails, doctorSpecializations);

        doctorSpecializations.forEach(doctorSpecialization -> doctorSpecialization.setDoctor(doctorToCreate));

        return doctorRepository.save(doctorToCreate);
    }

    @Transactional
    public String updatePwzNumber(String oldPwzNumber, String newPwzNumber) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByPwzNumber(oldPwzNumber);

        if (optionalDoctor.isEmpty()) {
            throw new DoctorNotExistException("Wybrany doktor nie istnieje!", HttpStatus.NOT_FOUND);
        }

        Doctor doctorToUpdate = optionalDoctor.get();
        doctorToUpdate.setPwzNumber(newPwzNumber);

        return doctorRepository.save(doctorToUpdate).getPwzNumber();
    }

    @Transactional
    public void delete(String pwzNumber) {
        Optional<Doctor> doctorOptional = doctorRepository.findByPwzNumber(pwzNumber);

        if (doctorOptional.isEmpty()) {
            throw new DoctorNotExistException("Doktor z numerem PWZ: %s nie istnieje".formatted(pwzNumber), HttpStatus.NOT_FOUND);
        }

        Doctor doctorToDelete = doctorOptional.get();

        doctorRepository.deleteById(doctorToDelete.getId());
    }

    public Doctor findByPwzNumber(String pwzNumber) {
        return doctorRepository.findByPwzNumber(pwzNumber)
                .orElseThrow(() -> new DoctorNotExistException("Doktor z numerem PWZ: %s nie istnieje".formatted(pwzNumber), HttpStatus.NOT_FOUND)
                );
    }

    private Doctor buildDoctor(DoctorFormDto doctorFormDto, Account createdDoctorAccount, PersonalDetails doctorPersonalDetails, Set<DoctorSpecialization> doctorSpecializations) {
        return Doctor.builder()
                .account(createdDoctorAccount)
                .personalDetails(doctorPersonalDetails)
                .description(doctorFormDto.description())
                .pwzNumber(doctorFormDto.pwzNumber())
                .dateOfEmployment(doctorFormDto.dateOfEmployment())
                .doctorSpecializations(doctorSpecializations)
                .build();
    }
}
