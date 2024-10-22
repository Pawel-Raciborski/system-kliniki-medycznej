package org.back.systemklinikimedycznej.auth.domain;

import lombok.Builder;
import org.back.systemklinikimedycznej.role.repository.entities.Role;

import java.util.List;
import java.util.UUID;

@Builder
public record LoginData(
        UUID sessionId,
        String username,
        String email,
        List<String> roles) {
}
