package org.back.systemklinikimedycznej.role.util;

import org.back.systemklinikimedycznej.role.controller.dto.RoleDto;
import org.back.systemklinikimedycznej.role.repository.entities.Role;

public class RoleManagementUtil {
    public static Role buildRoleWithName(RoleDto roleDto) {
        return Role.builder()
                .name(roleDto.name().toUpperCase())
                .description(roleDto.description())
                .build();
    }

    public static void setFieldsToUpdate(Role roleToUpdate, RoleDto roleUpdateData) {
        roleToUpdate.setDescription(roleUpdateData.description());
        roleToUpdate.setName(roleUpdateData.name());
    }
}
