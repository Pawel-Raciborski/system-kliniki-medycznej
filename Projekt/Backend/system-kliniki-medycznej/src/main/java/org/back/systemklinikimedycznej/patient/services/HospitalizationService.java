package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.back.systemklinikimedycznej.cure.service.MedicineService;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.controllers.dto.CreateHospitalizationRequest;
import org.back.systemklinikimedycznej.patient.controllers.dto.HospitalizationInfo;
import org.back.systemklinikimedycznej.patient.exceptions.HospitalizationException;
import org.back.systemklinikimedycznej.patient.mapper.HospitalizationMapper;
import org.back.systemklinikimedycznej.patient.repositories.HospitalizationRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;
import org.back.systemklinikimedycznej.patient.util.HospitalizationManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalizationService {
    private final HospitalizationRepository hospitalizationRepository;
    private final MedicineService medicineService;

    public Hospitalization findCurrentHospitalization(PatientDisease patientDisease) {
        return hospitalizationRepository.findCurrentHospitalization(patientDisease);
    }
    public List<HospitalizationInfo> getPagedHospitalizationHistoryForPatientDisease(PatientDisease patientDisease, Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page(),pagination.pageSize());

        return hospitalizationRepository.findHospitalizations(patientDisease,pageable).getContent().stream()
                .map(HospitalizationMapper.INSTANCE::mapFromEntity)
                .toList();
    }

    public List<Hospitalization> createHospitalizations(PatientDisease patientDisease, List<CreateHospitalizationRequest> hospitalizations) {
        List<Hospitalization> hospitalizationsToCreate = hospitalizations.stream().map(h -> buildHospitalization(patientDisease, h)).toList();
        return hospitalizationRepository.saveAll(hospitalizationsToCreate);
    }

    public Hospitalization save(Hospitalization hospitalization){
        return hospitalizationRepository.save(hospitalization);
    }

    public Hospitalization buildHospitalization(PatientDisease patientDisease, CreateHospitalizationRequest createHospitalizationRequest) {
        Medicine medicine = medicineService.findByRegistryNumber(createHospitalizationRequest.medicine().registryNumber());
        String dosage = createHospitalizationRequest.dosage();
        String notes = createHospitalizationRequest.notes();

        return HospitalizationManager.buildHospitalization(patientDisease, createHospitalizationRequest.finishDate(), medicine, dosage, notes);
    }



    public Hospitalization updateDiseaseHospitalization(Hospitalization hospitalization, HospitalizationInfo hospitalizationToUpdate) {
        HospitalizationManager.updateHospitalization(hospitalization,hospitalizationToUpdate);
        return hospitalizationRepository.save(hospitalization);
    }

    public Hospitalization findById(Long id){
        return hospitalizationRepository.findById(id).orElseThrow(
                () -> new HospitalizationException("Nie znaleziono hospitalizacji!", HttpStatus.NOT_FOUND)
        );
    }
}
