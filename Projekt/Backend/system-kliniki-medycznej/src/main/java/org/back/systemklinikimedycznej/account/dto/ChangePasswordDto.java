package org.back.systemklinikimedycznej.account.dto;

public record ChangePasswordDto(
        String userEmail,
        String previousPassword,
        String newPassword
) {
}
