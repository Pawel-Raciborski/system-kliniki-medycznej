package org.back.systemklinikimedycznej.prescription.mappers;

import org.back.systemklinikimedycznej.cure.dto.PrescriptionMedicineInfo;
import org.back.systemklinikimedycznej.prescription.repositories.entities.PrescriptionMedicine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrescriptionMedicineMapper {
    PrescriptionMedicineMapper INSTANCE = Mappers.getMapper(PrescriptionMedicineMapper.class);

    @Mappings({
            @Mapping(target = "medicineName", source = "medicine.commonName")
    })
    PrescriptionMedicineInfo mapToInfoFromEntity(PrescriptionMedicine prescriptionMedicine);
}
