package org.back.systemklinikimedycznej.patient.util;

import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.back.systemklinikimedycznej.patient.controllers.dto.HospitalizationInfo;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;

import java.time.LocalDate;

public class HospitalizationManager {

    public static void updateHospitalization(Hospitalization hospitalization, HospitalizationInfo hospitalizationInfo){
        hospitalization.setNotes(hospitalizationInfo.notes());
        hospitalization.setCureDosage(hospitalizationInfo.cureDosage());
        hospitalization.setFinishDate(hospitalizationInfo.finishDate());
        hospitalization.setMedicineUpdateDate(LocalDate.now());
    }
    public static Hospitalization buildHospitalization(PatientDisease patientDisease,LocalDate finishDate, Medicine medicine, String dosage, String notes) {
        return Hospitalization.builder()
                .medicine(medicine)
                .patientDisease(patientDisease)
                .cureDosage(dosage)
                .notes(notes)
                .medicineUpdateDate(LocalDate.now())
                .finishDate(finishDate)
                .build();
    }
}
