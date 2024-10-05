package org.back.systemklinikimedycznej.doctor.mapper;

import org.back.systemklinikimedycznej.doctor.controller.dto.OfficeHoursDto;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfficeHoursMapper {

    OfficeHoursMapper INSTANCE = Mappers.getMapper(OfficeHoursMapper.class);

    @Mappings({
            @Mapping(target = "startTime", source = "startHour"),
            @Mapping(target = "endTime", source = "endHour")
    })
    OfficeHoursDto mapFromEntity(DoctorOfficeHours doctorOfficeHours);
}
