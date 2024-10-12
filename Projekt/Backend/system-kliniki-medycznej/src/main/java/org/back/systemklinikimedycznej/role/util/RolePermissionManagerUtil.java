package org.back.systemklinikimedycznej.role.util;

import org.back.systemklinikimedycznej.role.repository.entities.Permission;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.back.systemklinikimedycznej.role.repository.entities.RolePermission;

public class RolePermissionManagerUtil {
    public static RolePermission buildRolePermission(Role role, Permission permission) {
        return RolePermission.builder()
                .role(role)
                .permission(permission)
                .build();
    }
}
