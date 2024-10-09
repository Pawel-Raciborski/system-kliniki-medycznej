package org.back.systemklinikimedycznej.receptionist.mapper;

import org.back.systemklinikimedycznej.receptionist.controller.dto.ReceptionistDto;
import org.back.systemklinikimedycznej.receptionist.repositories.entities.Receptionist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReceptionistMapper {
    ReceptionistMapper INSTANCE = Mappers.getMapper(ReceptionistMapper.class);

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
    ReceptionistDto mapFromEntity(Receptionist receptionist);
}
