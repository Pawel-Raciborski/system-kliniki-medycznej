package org.back.systemklinikimedycznej.prescription.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.back.systemklinikimedycznej.cure.service.MedicineService;
import org.back.systemklinikimedycznej.prescription.controller.dto.PrescriptionMedicineDto;
import org.back.systemklinikimedycznej.prescription.repositories.PrescriptionMedicineRepository;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.back.systemklinikimedycznej.prescription.repositories.entities.PrescriptionMedicine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionMedicineService {
    private final MedicineService medicineService;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    public void savePrescriptionMedicines(Prescription prescription, List<PrescriptionMedicineDto> prescriptionMedicinesDto) {
        List<PrescriptionMedicine> prescriptionMedicines = buildPrescriptionMedicineList(prescriptionMedicinesDto,prescription);
        prescriptionMedicineRepository.saveAll(prescriptionMedicines);
    }

    private List<PrescriptionMedicine> buildPrescriptionMedicineList(List<PrescriptionMedicineDto> prescriptionMedicineDtos,Prescription prescription) {
        return prescriptionMedicineDtos.stream()
                .map(prescriptionMedicineDto -> buildPrescriptionMedicine(prescriptionMedicineDto,prescription))
                .toList();
    }

    private PrescriptionMedicine buildPrescriptionMedicine(PrescriptionMedicineDto prescriptionMedicineDto, Prescription prescription) {
        Medicine medicine = medicineService.findByRegistryNumber(prescriptionMedicineDto.registryNumber());

        return PrescriptionMedicine.builder()
                .medicine(medicine)
                .dosage(prescriptionMedicineDto.dosage())
                .prescription(prescription)
                .build();
    }

    public List<PrescriptionMedicine> findAllWithPrescription(Prescription prescription) {
        return prescriptionMedicineRepository.findAllByPrescription(prescription);
    }
}
