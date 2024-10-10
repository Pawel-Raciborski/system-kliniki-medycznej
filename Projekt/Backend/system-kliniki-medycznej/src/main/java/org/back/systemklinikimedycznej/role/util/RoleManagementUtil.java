package org.back.systemklinikimedycznej.role.util;

import org.back.systemklinikimedycznej.role.dto.RoleCreateDto;
import org.back.systemklinikimedycznej.role.repository.entities.Role;

public class RoleManagementUtil {
    public static Role buildRoleWithName(RoleCreateDto roleCreateDto) {
        return Role.builder()
                .name(roleCreateDto.roleName())
                .description(roleCreateDto.description())
                .build();
    }
}
