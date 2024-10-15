package org.back.systemklinikimedycznej.auth.controller.dto;

public record Credentials(
        String login,
        String password
) {
}
