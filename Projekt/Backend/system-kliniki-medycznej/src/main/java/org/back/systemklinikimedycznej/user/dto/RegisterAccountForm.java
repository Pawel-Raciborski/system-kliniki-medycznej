package org.back.systemklinikimedycznej.user.dto;

public record RegisterAccountForm(
        String username,
        String password,
        String email
) {
}
