package org.back.systemklinikimedycznej.cure.mapper;

import org.back.systemklinikimedycznej.cure.dto.MedicineDto;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicineMapper {
    MedicineMapper INSTANCE = Mappers.getMapper(MedicineMapper.class);

    @Mappings({
            @Mapping(target = "apiMedicineId", source = "id")
    })
    Medicine mapFromDto(MedicineDto cure);


    default Medicine mapFromApiDto(MedicineDto medicineDto){
        if ( medicineDto == null ) {
            return null;
        }

        Medicine.MedicineBuilder medicine = Medicine.builder();

        medicine.apiMedicineId( medicineDto.id() );
        medicine.specimenType( medicineDto.specimenType() );
        medicine.medicinalProductName( medicineDto.medicinalProductName() );
        medicine.commonName( medicineDto.commonName() );
        medicine.pharmaceuticalFormName( medicineDto.pharmaceuticalFormName() );
        medicine.medicinalProductPower( medicineDto.medicinalProductPower() );
        medicine.activeSubstanceName( medicineDto.activeSubstanceName() );
        medicine.subjectMedicinalProductName( medicineDto.subjectMedicinalProductName() );
        medicine.registryNumber( medicineDto.registryNumber() );
        medicine.procedureTypeName( medicineDto.procedureTypeName() );
        medicine.expirationDateString( medicineDto.expirationDateString() );
        medicine.atcCode( medicineDto.atcCode() );
        medicine.targetSpecies( medicineDto.targetSpecies() );

        return medicine.build();
    }
}
