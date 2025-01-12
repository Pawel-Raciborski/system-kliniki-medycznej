package org.back.systemklinikimedycznej.prescription.util;

import org.back.systemklinikimedycznej.doctor.mapper.DoctorMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.patient.mapper.PatientMapper;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.prescription.controller.dto.PrescriptionInfo;
import org.back.systemklinikimedycznej.prescription.dto.PrescriptionDetails;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.back.systemklinikimedycznej.prescription.repositories.entities.PrescriptionMedicine;

import java.time.LocalDate;

public class PrescriptionManagerUtil {
    public static Prescription buildPrescription(Doctor doctor, Patient patient, LocalDate expirationDate, String description) {
        return Prescription.builder()
                .doctor(doctor)
                .patient(patient)
                .expirationDate(expirationDate)
                .createdDate(LocalDate.now())
                .description(description)
                .build();
    }

    public static PrescriptionDetails buildPrescriptionDetails(Prescription prescription) {
        return PrescriptionDetails.builder()
                .id(prescription.getId())
                .createdAt(prescription.getCreatedDate())
                .expirationDate(prescription.getExpirationDate())
                .description(prescription.getDescription())
                .prescriptionMedicineInfoList(PrescriptionMedicineManagerUtil.buildPrescriptionMedicineInfoList(prescription.getPrescriptionMedicines()))
                .doctorInfo(DoctorMapper.INSTANCE.mapToDoctorInfo(prescription.getDoctor()))
                .patient(PatientMapper.INSTANCE.mapFromEntity(prescription.getPatient()))
                .build();
    }

    public static PrescriptionInfo buildPrescriptionInfo(Prescription prescription) {
        Doctor doctor = prescription.getDoctor();

        return PrescriptionInfo.builder()
                .uuid(prescription.getId())
                .description(prescription.getDescription())
                .doctor(DoctorMapper.INSTANCE.mapToDoctorInfo(doctor))
                .expirationDate(prescription.getExpirationDate())
                .createdDate(prescription.getCreatedDate())
                .build();
    }
}
