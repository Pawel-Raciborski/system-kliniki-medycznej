package org.back.systemklinikimedycznej.role.mapper;

import org.back.systemklinikimedycznej.role.controller.dto.PermissionDto;
import org.back.systemklinikimedycznej.role.repository.entities.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper {
    public static final PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    PermissionDto mapFromEntity(Permission permission);

}
