package org.back.systemklinikimedycznej.user.dto;

public record AccountDto(
        String username,
        String password,
        String email
) {
}
