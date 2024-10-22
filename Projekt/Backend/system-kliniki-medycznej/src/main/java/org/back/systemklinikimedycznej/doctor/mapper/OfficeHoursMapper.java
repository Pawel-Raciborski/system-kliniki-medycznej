package org.back.systemklinikimedycznej.doctor.mapper;

import org.back.systemklinikimedycznej.doctor.controller.dto.OfficeHoursDto;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfficeHoursMapper {

    OfficeHoursMapper INSTANCE = Mappers.getMapper(OfficeHoursMapper.class);


    OfficeHoursDto mapFromEntity(DoctorOfficeHours doctorOfficeHours);
}
