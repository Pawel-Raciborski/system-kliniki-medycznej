package org.back.systemklinikimedycznej.disease.mapper;

import org.back.systemklinikimedycznej.disease.dto.BasicDiseaseInfo;
import org.back.systemklinikimedycznej.disease.repository.entities.Disease;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiseaseMapper {
    DiseaseMapper INSTANCE = Mappers.getMapper(DiseaseMapper.class);

    @Mappings({
            @Mapping(target = "code",source = "icd11Code"),
            @Mapping(target = "title",source = "icd11Title")
    })
    BasicDiseaseInfo mapToBasicDiseaseInfo(Disease disease);
}
