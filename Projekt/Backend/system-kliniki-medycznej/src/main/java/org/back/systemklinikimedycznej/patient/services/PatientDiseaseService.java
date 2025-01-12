package org.back.systemklinikimedycznej.patient.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.disease.dto.SearchDisease;
import org.back.systemklinikimedycznej.disease.repository.entities.Disease;
import org.back.systemklinikimedycznej.disease.services.DiseaseService;
import org.back.systemklinikimedycznej.doctor.domain.PrefixBuilder;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.back.systemklinikimedycznej.doctor.util.DoctorManagerUtil;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.controllers.dto.CreatePatientDiseaseRequest;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDiseaseHospitalizationInfo;
import org.back.systemklinikimedycznej.patient.domain.CureStatus;
import org.back.systemklinikimedycznej.patient.exceptions.PatientDiseaseException;
import org.back.systemklinikimedycznej.patient.repositories.PatientDiseaseRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;
import org.back.systemklinikimedycznej.patient.util.PatientDiseaseManagerUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.back.systemklinikimedycznej.doctor.services.DoctorService.isNotEmptyAndBlank;

@Service
@RequiredArgsConstructor
public class PatientDiseaseService {
    private final PatientDiseaseRepository patientDiseaseRepository;
    private final HospitalizationService hospitalizationService;
    private final PatientCardService patientCardService;
    private final DiseaseService diseaseService;
    private final DoctorService doctorService;
    private final EntityManager entityManager;

    public Long countPatientDiseases(PatientCard patientCard){
        return patientDiseaseRepository.countPatientDiseases(patientCard);
    };

    public List<PatientDiseaseHospitalizationInfo> getPagedPatientDiseasesHospitalizationInfo(String patientCardId, String cureStatus , Pagination pagination) {
        PatientCard patientCard = patientCardService.findById(UUID.fromString(patientCardId));
        List<CureStatus> cureStatusList = CureStatus.findAllByStatus(cureStatus);
        PageRequest pageRequest = PageRequest.of(pagination.page(), pagination.pageSize());

        List<PatientDisease> patientDiseases = patientDiseaseRepository.findByPatientCardAndWithCureStatusPaged(patientCard,cureStatusList,pageRequest).getContent();
        return patientDiseases.stream().map(this::createHospitalizationInfo).toList();
    }

    private PatientDiseaseHospitalizationInfo createHospitalizationInfo(PatientDisease patientDisease) {
        Hospitalization currentHospitalization = hospitalizationService.findCurrentHospitalization(patientDisease);
        String fullName = DoctorManagerUtil.createDoctorFullName(patientDisease.getDetectedDoctor());

        return PatientDiseaseHospitalizationInfo.buildPatientDiseaseHospitalization(patientDisease,currentHospitalization,fullName,patientDisease.getDisease());
    }

    public PatientDisease findById(Long id) {
        return patientDiseaseRepository.findById(id).orElseThrow(() -> new PatientDiseaseException(
                "Nie znaleziono choroby pacjenta o podanym identyfikatorze",
                HttpStatus.NOT_FOUND
        ));
    }

    @Transactional
    public PatientDisease create(CreatePatientDiseaseRequest createPatientDiseaseRequest) {
        PatientCard patientCard = patientCardService.findById(createPatientDiseaseRequest.patientCardId());
        Disease disease = diseaseService.findById(createPatientDiseaseRequest.diseaseDto().id());
        Doctor doctor = doctorService.findById(createPatientDiseaseRequest.doctorId());

        PatientDisease patientDisease = PatientDiseaseManagerUtil.buildPatientDisease(patientCard, disease, doctor, createPatientDiseaseRequest.description());
        PatientDisease createdPatientDisease = patientDiseaseRepository.save(patientDisease);
        hospitalizationService.createHospitalizations(createdPatientDisease,createPatientDiseaseRequest.hospitalizations());
        return patientDisease;
    }

    @Transactional
    public List<PatientDiseaseHospitalizationInfo> searchPatientDiseasesHospitalization(UUID patientCardId, SearchDisease searchDisease, Pagination pagination) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatientDisease> query = criteriaBuilder.createQuery(PatientDisease.class);
        Root<PatientDisease> root = query.from(PatientDisease.class);
        List<Predicate> predicates = new ArrayList<>();

        Join<PatientDisease, Disease> disease = root.join("disease", JoinType.INNER);
        Join<PatientDisease, PatientCard> patientCard = root.join("patientCard", JoinType.INNER);

        String diseaseName = searchDisease.name();
        String diseaseCode = searchDisease.code();

        if(isNotEmptyAndBlank(diseaseName)){
            predicates.add(criteriaBuilder.like(disease.get("icd11Title"), PrefixBuilder.startsWith(diseaseName)));
        }
        if(isNotEmptyAndBlank(diseaseCode)){
            predicates.add(criteriaBuilder.like(disease.get("icd11Code"), PrefixBuilder.startsWith(diseaseCode)));
        }
        predicates.add(criteriaBuilder.equal(patientCard.<UUID>get("id"),patientCardId));

        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<PatientDisease> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pagination.page() * pagination.pageSize());
        typedQuery.setMaxResults(pagination.pageSize());

        List<PatientDisease> patientDiseases = typedQuery.getResultList();
        return patientDiseases.stream().map(this::createHospitalizationInfo).toList();
    }
}
