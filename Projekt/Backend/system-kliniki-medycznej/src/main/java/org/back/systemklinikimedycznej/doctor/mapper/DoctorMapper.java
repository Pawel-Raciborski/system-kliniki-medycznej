package org.back.systemklinikimedycznej.doctor.mapper;

import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorDto;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "personalDetails.name"),
            @Mapping(target = "surname", source = "personalDetails.surname"),
            @Mapping(target = "email", source = "account.email"),
            @Mapping(target = "phoneNumber", source = "personalDetails.phoneNumber"),
            @Mapping(target = "street", source = "personalDetails.address.street"),
            @Mapping(target = "postalCode", source = "personalDetails.address.postalCode"),
            @Mapping(target = "apartmentNumber", source = "personalDetails.address.apartmentNumber"),
            @Mapping(target = "city", source = "personalDetails.address.city.name"),
    })
    DoctorDto mapToDto(Doctor doctor);
}
