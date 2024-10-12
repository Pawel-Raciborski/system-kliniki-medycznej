package org.back.systemklinikimedycznej.role.util;

import org.back.systemklinikimedycznej.role.controller.dto.PermissionDto;
import org.back.systemklinikimedycznej.role.repository.entities.Permission;

public class PermissionManagerUtil {
    public static Permission buildPermission(PermissionDto permission) {
        return Permission.builder()
                .name(permission.name())
                .description(permission.description())
                .build();
    }
}
