package org.back.systemklinikimedycznej.auth.domain;

import lombok.Builder;
import org.back.systemklinikimedycznej.role.repository.entities.Role;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
public record LoginData(
        Map<String,Object> data
) {
}
