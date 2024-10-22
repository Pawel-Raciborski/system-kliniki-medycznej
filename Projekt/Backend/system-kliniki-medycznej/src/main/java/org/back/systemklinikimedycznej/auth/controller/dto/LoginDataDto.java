package org.back.systemklinikimedycznej.auth.controller.dto;

import org.back.systemklinikimedycznej.role.controller.dto.RoleDto;

import java.util.List;

public record LoginDataDto(
        String sessionId,
        String username,
        String email,
        List<String> roles
) {
}
