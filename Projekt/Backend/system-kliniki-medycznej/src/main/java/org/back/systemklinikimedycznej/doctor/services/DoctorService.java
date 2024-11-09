package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.doctor.controller.dto.RegisterDoctorDetails;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorNotFoundException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.back.systemklinikimedycznej.doctor.util.DoctorManagerUtil;
import org.back.systemklinikimedycznej.doctor.validators.DoctorValidator;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.personal_details.services.PersonalDetailsService;
import org.back.systemklinikimedycznej.role.enums.BasicAppRoles;
import org.back.systemklinikimedycznej.role.services.AccountRoleService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorValidator doctorValidator;
    private final AccountService accountService;
    private final PersonalDetailsService personalDetailsService;
    private final DoctorRepository doctorRepository;
    private final CalendarService calendarService;
    private final DoctorSpecializationsService doctorSpecializationsService;
    private final AccountRoleService accountRoleService;
    private final DoctorFileService doctorFileService;

    @Transactional
    public Doctor create(RegisterDoctorDetails registerDoctorDetails, MultipartFile profileImage) {
        doctorValidator.validatePwzNumber(registerDoctorDetails.pwzNumber());

        Account createdDoctorAccount = accountService.create(registerDoctorDetails.registerAccountData());
        PersonalDetails doctorPersonalDetails = personalDetailsService.create(registerDoctorDetails.personalDetails());
        URI profileImagePath = doctorFileService.saveProfileImage(registerDoctorDetails, profileImage);
        log.info("Profile image source {}", profileImagePath);
        Doctor doctorToCreate = DoctorManagerUtil.buildDoctor(registerDoctorDetails, createdDoctorAccount, doctorPersonalDetails)
                .withProfileImagePath(profileImagePath.toString());
        Doctor createdDoctor = saveDoctor(doctorToCreate);

        accountRoleService.processAccountRoleCreation(createdDoctorAccount, BasicAppRoles.DOCTOR.name());

        return postDoctorCreateOperations(registerDoctorDetails, createdDoctor);
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
        PersonalDetails personalDetailsToRemove = doctorToRemove.getPersonalDetails();
        doctorFileService.deleteDirectory(doctorToRemove.getProfileImagePath());
        doctorRepository.deleteById(doctorToRemove.getId());
        personalDetailsService.deletePersonalDetails(personalDetailsToRemove);
    }

    public Doctor findByPwzNumber(String pwzNumber) {
        return doctorRepository.findByPwzNumber(pwzNumber).orElseThrow(
                () -> new DoctorNotFoundException("Doktor z numerem PWZ: %s nie istnieje".formatted(pwzNumber), HttpStatus.NOT_FOUND)
        );
    }

    private Doctor saveDoctor(Doctor doctorToCreate) {
        return doctorRepository.save(doctorToCreate);
    }

    public Doctor postDoctorCreateOperations(RegisterDoctorDetails registerDoctorDetails, Doctor createdDoctor) {
        calendarService.createCalendarForDoctor(createdDoctor);

        List<DoctorSpecialization> doctorSpecializations = doctorSpecializationsService.addSpecializationToDoctor(createdDoctor, registerDoctorDetails.doctorSpecializations());

        return createdDoctor.withDoctorSpecializations(new HashSet<>(doctorSpecializations));
    }

    public Resource getFile(Doctor doctor, String fileName) {
        return doctorFileService.findFile(doctor.getProfileImagePath(),fileName);
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElseThrow(
                () -> new DoctorNotFoundException("Nie znaleziono lekarza",HttpStatus.NOT_FOUND)
        );
    }
}
