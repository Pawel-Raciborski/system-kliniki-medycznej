package org.back.systemklinikimedycznej.prescription.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.services.PatientService;
import org.back.systemklinikimedycznej.prescription.controller.dto.PrescriptionInfo;
import org.back.systemklinikimedycznej.prescription.dto.CreatePrescriptionForm;
import org.back.systemklinikimedycznej.prescription.dto.PrescriptionDetails;
import org.back.systemklinikimedycznej.prescription.exceptions.PrescriptionException;
import org.back.systemklinikimedycznej.prescription.repositories.PrescriptionRepository;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.back.systemklinikimedycznej.prescription.repositories.entities.PrescriptionMedicine;
import org.back.systemklinikimedycznej.prescription.util.PrescriptionManagerUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final PrescriptionMedicineService prescriptionMedicineService;
    private final PrescriptionRepository prescriptionRepository;

    @Transactional
    public PrescriptionInfo create(CreatePrescriptionForm createPrescriptionForm) {
        Doctor doctor = doctorService.findById(createPrescriptionForm.doctorId());
        Patient patient = patientService.findById(createPrescriptionForm.patientId());


        Prescription prescriptionToCreate = PrescriptionManagerUtil.buildPrescription(doctor, patient, createPrescriptionForm.expirationDate(), createPrescriptionForm.description());
        Prescription savedPrescription = prescriptionRepository.save(prescriptionToCreate);
        prescriptionMedicineService.savePrescriptionMedicines(savedPrescription,createPrescriptionForm.prescriptionMedicineList());

        return PrescriptionManagerUtil.buildPrescriptionInfo(savedPrescription);
    }

    @Transactional
    public PrescriptionDetails getPrescriptionDetails(UUID id) {
        Prescription prescription = findById(id);
        return PrescriptionManagerUtil.buildPrescriptionDetails(prescription);
    }

    private Prescription findById(UUID id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new PrescriptionException("Nie znaleziono recepty z identyfikatorem [%s]".formatted(id), HttpStatus.NOT_FOUND));
    }

    public Long countPatientPrescriptions(Patient patient) {
        return prescriptionRepository.countPatientPrescriptions(patient);
    }

    public List<PrescriptionInfo> findPatientPrescriptions(Long patientId, Pagination pagination) {
        Patient patient = patientService.findById(patientId);
        Pageable pageRequest = PageRequest.of(pagination.page(),pagination.pageSize());

        return prescriptionRepository.findPatientPrescriptions(patient,pageRequest).getContent()
                .stream()
                .map(PrescriptionManagerUtil::buildPrescriptionInfo)
                .toList();
    }
}
