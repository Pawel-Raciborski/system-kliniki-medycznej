package org.back.systemklinikimedycznej.personal_details.mapper;

import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonalDetailsMapper {
    PersonalDetailsMapper INSTANCE = Mappers.getMapper(PersonalDetailsMapper.class);

    @Mappings({
            @Mapping(target = "address.city", source = "address.city.name")
    })
    PersonalDetailsDto mapFromEntity(PersonalDetails personalDetails);
}
