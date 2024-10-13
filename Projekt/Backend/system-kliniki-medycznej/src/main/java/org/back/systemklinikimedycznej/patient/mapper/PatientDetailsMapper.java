package org.back.systemklinikimedycznej.patient.mapper;

import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDetailsDto;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientDetailsMapper {
    PatientDetailsMapper INSTANCE = Mappers.getMapper(PatientDetailsMapper.class);

    @Mappings({
            @Mapping(target = "bloodType",source = "bloodType.bloodTypeName")
    })
    PatientDetailsDto mapFromEntity(PatientDetails patientDetails);
}
