package org.back.systemklinikimedycznej.doctor.mapper;

import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorDto;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    DoctorDto mapToDto(Doctor doctor);
}
