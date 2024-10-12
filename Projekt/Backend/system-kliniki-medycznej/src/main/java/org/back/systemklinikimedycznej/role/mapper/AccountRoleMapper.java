package org.back.systemklinikimedycznej.role.mapper;

import org.back.systemklinikimedycznej.role.controller.dto.AccountRoleDto;
import org.back.systemklinikimedycznej.role.repository.entities.AccountRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountRoleMapper {
    AccountRoleMapper INSTANCE = Mappers.getMapper(AccountRoleMapper.class);

    @Mappings({
            @Mapping(target = "username", source = "account.username"),
            @Mapping(target = "roleName", source = "role.name")
    })
    AccountRoleDto mapFromEntity(AccountRole accountRole);
}
