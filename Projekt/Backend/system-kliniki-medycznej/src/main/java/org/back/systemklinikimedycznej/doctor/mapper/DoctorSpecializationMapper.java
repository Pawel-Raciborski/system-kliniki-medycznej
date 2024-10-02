package org.back.systemklinikimedycznej.doctor.mapper;

import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorSpecializationDto;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorSpecializationMapper {
    DoctorSpecializationMapper INSTANCE = Mappers.getMapper(DoctorSpecializationMapper.class);

    DoctorSpecialization mapFromDto(DoctorSpecializationDto doctorSpecializationDto);
}
