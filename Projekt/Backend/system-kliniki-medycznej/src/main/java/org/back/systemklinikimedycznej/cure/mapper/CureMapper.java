package org.back.systemklinikimedycznej.cure.mapper;

import org.back.systemklinikimedycznej.cure.dto.MedicineDto;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CureMapper {
    CureMapper INSTANCE = Mappers.getMapper(CureMapper.class);

    @Mappings({
            @Mapping(target = "apiMedicineId", source = "id")
    })
    Medicine mapFromDto(MedicineDto cure);
}
