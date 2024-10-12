package org.back.systemklinikimedycznej.role.mapper;

import org.back.systemklinikimedycznej.role.controller.dto.RolePermissionDto;
import org.back.systemklinikimedycznej.role.repository.entities.RolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolePermissionMapper {
    RolePermissionMapper INSTANCE = Mappers.getMapper(RolePermissionMapper.class);

    @Mappings({
            @Mapping(target = "roleName",source = "role.name"),
            @Mapping(target = "permissionName",source = "permission.name")
    })
    RolePermissionDto mapFromEntity(RolePermission rolePermission);
}
