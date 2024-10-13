package org.back.systemklinikimedycznej.patient.mapper;

import org.back.systemklinikimedycznej.patient.controllers.dto.RegisteredPatientData;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mappings({
            @Mapping(target = "personalDetails.address.city", source = "personalDetails.address.city.name"),
    })
    RegisteredPatientData mapFromEntity(Patient patient);
}
