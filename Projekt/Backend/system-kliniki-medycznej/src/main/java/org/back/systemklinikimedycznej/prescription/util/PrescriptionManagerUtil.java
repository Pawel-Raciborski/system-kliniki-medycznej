package org.back.systemklinikimedycznej.prescription.util;

import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.prescription.dto.CreatePrescriptionForm;
import org.back.systemklinikimedycznej.prescription.dto.PrescriptionMedicineDto;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.back.systemklinikimedycznej.prescription.repositories.entities.PrescriptionMedicine;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
}
