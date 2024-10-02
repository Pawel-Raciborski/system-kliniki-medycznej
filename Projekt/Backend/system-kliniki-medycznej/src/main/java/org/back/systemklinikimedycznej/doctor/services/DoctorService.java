package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.RegisterDoctorForm;
import org.back.systemklinikimedycznej.doctor.exceptions.PwzNumberException;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorSpecializationMapper;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.personal_details.services.PersonalDetailsService;
import org.back.systemklinikimedycznej.user.repositories.entities.Account;
import org.back.systemklinikimedycznej.user.services.AccountService;
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
    public Doctor create(RegisterDoctorForm registerDoctorForm) {
        Optional<Doctor> doctorOpt = doctorRepository.findByPwzNumber(registerDoctorForm.pwzNumber());

        if(doctorOpt.isPresent()){
            throw new PwzNumberException("Podany numer pwz już zajęty!",HttpStatus.CONFLICT);
        }

        Account createdDoctorAccount = accountService.create(registerDoctorForm.registerAccountData());
        PersonalDetails doctorPersonalDetails = personalDetailsService.create(registerDoctorForm.personalDetails());
        Set<DoctorSpecialization> doctorSpecializations = registerDoctorForm.doctorSpecializations().stream()
                .map(DoctorSpecializationMapper.INSTANCE::mapFromDto).collect(Collectors.toSet());

        Doctor doctorToCreate = Doctor.builder()
                .account(createdDoctorAccount)
                .personalDetails(doctorPersonalDetails)
                .pwzNumber(registerDoctorForm.pwzNumber())
                .dateOfEmployment(registerDoctorForm.dateOfEmployment())
                .doctorSpecializations(doctorSpecializations)
                .build();

        doctorSpecializations.forEach(doctorSpecialization -> doctorSpecialization.setDoctor(doctorToCreate));

        return doctorRepository.save(doctorToCreate);
    }
}
