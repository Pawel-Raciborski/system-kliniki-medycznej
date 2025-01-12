package org.back.systemklinikimedycznej.role.controller.dto;

import java.util.List;

public record RoleDetails(
        RoleDto role,
        List<PermissionDto> permissions
) {
}
