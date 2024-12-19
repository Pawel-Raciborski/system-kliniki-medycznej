package org.back.systemklinikimedycznej.doctor.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorInfo;
import org.back.systemklinikimedycznej.doctor.controller.dto.RegisterDoctorDetails;
import org.back.systemklinikimedycznej.doctor.domain.AdvancedSearch;
import org.back.systemklinikimedycznej.doctor.domain.DoctorSearchParams;
import org.back.systemklinikimedycznej.doctor.domain.PrefixBuilder;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorNotFoundException;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorMapper;
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
import java.util.*;

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
    private final EntityManager entityManager;

    public static boolean isNotEmptyAndBlank(String message) {
        return Objects.nonNull(message) && !message.isBlank() && !message.isEmpty();
    }

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
        return doctorFileService.findFile(doctor.getProfileImagePath(), fileName);
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElseThrow(
                () -> new DoctorNotFoundException("Nie znaleziono lekarza", HttpStatus.NOT_FOUND)
        );
    }

    public Long countAvailableDoctors() {
        return doctorRepository.count();
    }

    public Doctor findByAccount(Account account) {
        return doctorRepository.findByAccount(account)
                .orElseThrow(
                        () -> new DoctorNotFoundException("Nie znaleziono doktora!", HttpStatus.NOT_FOUND)
                );
    }

    @Transactional
    public List<DoctorInfo> searchForDoctors(DoctorSearchParams doctorSearchParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Doctor> query = criteriaBuilder.createQuery(Doctor.class);
        Root<Doctor> doctorRoot = query.from(Doctor.class);
        query.select(doctorRoot);
        List<Predicate> predicates = new ArrayList<>();

        String doctorFullName = doctorSearchParams.doctorFullName();
        String[] doctorNameAndSurname = doctorFullName.trim().split(" ");
        if (isNotEmptyAndBlank(doctorFullName)) {
            String name = doctorNameAndSurname[0];
            String surname = getSurname(doctorNameAndSurname);
            Join<Doctor, PersonalDetails> personalDetails = doctorRoot.join("personalDetails");

            if(isNotEmptyAndBlank(name)){
                predicates.add(criteriaBuilder.like(personalDetails.get("name"), PrefixBuilder.startsWith(name)));
            }
            if(isNotEmptyAndBlank(surname)){

                predicates.add(criteriaBuilder.like(personalDetails.get("surname"), PrefixBuilder.startsWith(surname)));
            }
        }

        if (!Objects.isNull(doctorSearchParams.advancedSearch())) {
            AdvancedSearch advancedSearch = doctorSearchParams.advancedSearch();
            List<String> specializations = advancedSearch.selectedSpecializations();
            String doctorPwzNumber = advancedSearch.pwzNumber();

            if (Objects.nonNull(specializations) && !specializations.isEmpty()) {
                Join<Doctor, DoctorSpecialization> doctorDoctorSpecializationJoin = doctorRoot.join("doctorSpecializations");
                predicates.add(doctorDoctorSpecializationJoin.get("name").in(specializations));
            }

            if (isNotEmptyAndBlank(doctorPwzNumber)) {
                predicates.add(criteriaBuilder.like(doctorRoot.get("pwzNumber"),PrefixBuilder.startsWith(doctorPwzNumber)));
            }
        }

        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList().stream()
                .map(DoctorMapper.INSTANCE::mapToDoctorInfo)
                .toList();
    }

    private static String getSurname(String[] doctorNameAndSurname) {
        return doctorNameAndSurname.length == 2 ? doctorNameAndSurname[1] : "";
    }
}
