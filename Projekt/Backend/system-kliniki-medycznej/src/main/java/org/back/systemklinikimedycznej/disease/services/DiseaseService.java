package org.back.systemklinikimedycznej.disease.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.disease.dto.DiseaseDto;
import org.back.systemklinikimedycznej.disease.dto.SearchDisease;
import org.back.systemklinikimedycznej.disease.exceptions.DiseaseException;
import org.back.systemklinikimedycznej.disease.repository.DiseaseRepository;
import org.back.systemklinikimedycznej.disease.repository.entities.Disease;
import org.back.systemklinikimedycznej.doctor.domain.PrefixBuilder;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.controllers.dto.CreatePatientDiseaseRequest;
import org.back.systemklinikimedycznej.patient.repositories.PatientDiseaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.back.systemklinikimedycznej.doctor.services.DoctorService.isNotEmptyAndBlank;

@Service
@RequiredArgsConstructor
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;
    private final EntityManager entityManager;

    public Disease findById(Long id) {
        return diseaseRepository.findById(id).orElseThrow(
                () -> new DiseaseException("Nie znaleziono podanej choroby", HttpStatus.NOT_FOUND)
        );
    }

    @Transactional
    public List<Disease> searchDiseases(SearchDisease searchDisease, Pagination pagination) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Disease> query = criteriaBuilder.createQuery(Disease.class);
        Root<Disease> root = query.from(Disease.class);
        List<Predicate> predicates = new ArrayList<>();

        String diseaseName = searchDisease.name();
        String diseaseCode = searchDisease.code();

        if(isNotEmptyAndBlank(diseaseName)){
            predicates.add(criteriaBuilder.like(root.get("icd11Title"), PrefixBuilder.startsWith(diseaseName)));
        }

        if(isNotEmptyAndBlank(diseaseCode)){
            predicates.add(criteriaBuilder.like(root.get("icd11Code"),PrefixBuilder.startsWith(diseaseCode)));
        }

        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Disease> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pagination.page() * pagination.pageSize());
        typedQuery.setMaxResults(pagination.pageSize());

        return typedQuery.getResultList();
    }
}
