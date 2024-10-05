package org.back.systemklinikimedycznej.account.dto;

public record AccountDto(
        String username,
        String password,
        String email
) {
}
