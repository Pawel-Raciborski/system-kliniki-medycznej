package org.back.systemklinikimedycznej.account.dto;

import lombok.Builder;

@Builder
public record AccountDto(
        String username,
        String password,
        String email
) {
}
