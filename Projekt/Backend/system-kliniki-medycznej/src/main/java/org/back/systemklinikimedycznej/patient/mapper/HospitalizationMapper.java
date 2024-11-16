package org.back.systemklinikimedycznej.patient.mapper;

import org.back.systemklinikimedycznej.patient.controllers.dto.HospitalizationInfo;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HospitalizationMapper {
    HospitalizationMapper INSTANCE = Mappers.getMapper(HospitalizationMapper.class);

    HospitalizationInfo mapFromEntity(Hospitalization hospitalization);
}
