package org.back.systemklinikimedycznej.prescription.util;

import org.back.systemklinikimedycznej.cure.dto.PrescriptionMedicineInfo;
import org.back.systemklinikimedycznej.prescription.mappers.PrescriptionMedicineMapper;
import org.back.systemklinikimedycznej.prescription.repositories.entities.PrescriptionMedicine;

import java.util.List;
import java.util.Set;

public class PrescriptionMedicineManagerUtil {

    public static List<PrescriptionMedicineInfo> buildPrescriptionMedicineInfoList(Set<PrescriptionMedicine> prescriptionMedicines) {
        return prescriptionMedicines.stream().map(PrescriptionMedicineMapper.INSTANCE::mapToInfoFromEntity).toList();
    }
}
